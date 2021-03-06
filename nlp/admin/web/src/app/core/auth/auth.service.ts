/*
 * Copyright (C) 2017 VSCT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import {Injectable} from "@angular/core";
import {AuthListener} from "./auth.listener";
import {Router} from "@angular/router";
import {AuthenticateResponse, AuthenticateRequest} from "../../model/auth";
import {Observable} from "rxjs";
import {RestService} from "../rest/rest.service";
import {environment} from "../../../environments/environment";

@Injectable()
export class AuthService {

  private logged: boolean;
  private redirectUrl: string;
  private authListeners: AuthListener[] = [];

  constructor(private rest: RestService, private router: Router) {
  }

  setRedirectUrl(redirectUrl: string) {
    this.redirectUrl = redirectUrl;
  }

  getRedirectUrl() {
    return this.redirectUrl ? this.redirectUrl : "/";
  }

  isLoggedIn(): boolean {
    return this.logged;
  }

  addListener(listener: AuthListener) {
    this.authListeners.push(listener);
  }

  login(password: string, response: AuthenticateResponse): boolean {
    if (response.authenticated) {
      this.rest.setAuthToken(btoa(`${response.email}:${password}`));
      this.logged = true;
      this.authListeners.forEach(l => l.login(response.toUser()));
    }
    return response.authenticated;
  }

  logout() {
    this.rest.postNotAuthenticated("/logout")
      .subscribe(_ => {
        this.logged = false;
        this.rest.setAuthToken(null);
        this.authListeners.forEach(l => l.logout());
        this.router.navigateByUrl("/login");
      });
  }

  authenticate(email: string, password: string): Observable<boolean> {
    return this.rest.postNotAuthenticated(
      '/authenticate',
      new AuthenticateRequest(email, password),
      (j => this.login(password, AuthenticateResponse.fromJSON(j)) ));
  }
}
