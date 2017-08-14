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

package fr.vsct.tock.bot.connector.ga

import fr.vsct.tock.bot.connector.Connector
import fr.vsct.tock.bot.connector.ConnectorConfiguration
import fr.vsct.tock.bot.connector.ConnectorProvider
import fr.vsct.tock.bot.connector.ConnectorType

/**
 *
 */
object GAConnectorProvider : ConnectorProvider {

    override val connectorType: ConnectorType = gaConnectorType
    override fun connector(connectorConfiguration: ConnectorConfiguration): Connector {
        with(connectorConfiguration) {
            return GAConnector(
                    applicationId,
                    path)
        }
    }

    /**
     * Create a new messenger connector configuration.
     */
    fun newConfiguration(
            applicationId: String = "ga",
            path: String = "/ga"): ConnectorConfiguration {


        return ConnectorConfiguration(
                applicationId,
                path,
                connectorType,
                emptyMap())
    }
}