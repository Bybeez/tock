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

package fr.vsct.tock.bot.connector.rocketchat

import fr.vsct.tock.bot.connector.Connector
import fr.vsct.tock.bot.connector.ConnectorConfiguration
import fr.vsct.tock.bot.connector.ConnectorProvider
import fr.vsct.tock.bot.connector.ConnectorType
import fr.vsct.tock.bot.connector.ConnectorTypeConfiguration
import fr.vsct.tock.bot.connector.ConnectorTypeConfigurationField
import fr.vsct.tock.shared.resourceAsString

/**
 *
 */
internal object RocketChatConnectorProvider : ConnectorProvider {

    private const val ROCKET_CHAT_URL = "_url_"
    private const val LOGIN = "_login_"
    private const val PASSWORD = "_password_"
    private const val AVATAR = "_avatar_"

    override val connectorType: ConnectorType get() = rocketChatConnectorType

    override fun connector(connectorConfiguration: ConnectorConfiguration): Connector {
        with(connectorConfiguration) {
            return RocketChatConnector(
                connectorId,
                RocketChatClient(
                    parameters.getValue(ROCKET_CHAT_URL),
                    parameters.getValue(LOGIN),
                    parameters.getValue(PASSWORD),
                    parameters.getValue(AVATAR)
                )
            )
        }
    }

    override fun configuration(): ConnectorTypeConfiguration =
        ConnectorTypeConfiguration(
            rocketChatConnectorType,
            listOf(
                ConnectorTypeConfigurationField(
                    "Rocket.Chat Server Url",
                    ROCKET_CHAT_URL,
                    true
                ),
                ConnectorTypeConfigurationField(
                    "Bot Login",
                    LOGIN,
                    true
                ),
                ConnectorTypeConfigurationField(
                    "Bot Password",
                    PASSWORD,
                    true
                ),
                ConnectorTypeConfigurationField(
                    "Avatar Url",
                    AVATAR,
                    true
                )
            ),
            resourceAsString("/rocketchat.svg")
        )

}

internal class RocketChatConnectorProviderService : ConnectorProvider by RocketChatConnectorProvider