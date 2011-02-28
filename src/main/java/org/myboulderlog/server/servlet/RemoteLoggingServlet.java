/*
 * Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.myboulderlog.server.servlet;

import com.google.gwt.logging.shared.RemoteLoggingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Server side code for the remote log handler.
 */
@Singleton
public class RemoteLoggingServlet extends RemoteServiceServlet implements RemoteLoggingService {
    private static Logger logger = LoggerFactory.getLogger(RemoteLoggingServlet.class.getName());

    private static String loggerNameOverride = null;

    /**
     * Logs a Log Record which has been serialized using GWT RPC on the server.
     *
     * @return either an error message, or null if logging is successful.
     */
    public final String logOnServer(LogRecord lr) {
        try {
            String loggerName = loggerNameOverride == null ? lr.getLoggerName() : loggerNameOverride;
            Logger logger = LoggerFactory.getLogger(loggerName);
            if (lr.getLevel().equals(Level.SEVERE)) {
                logger.error(lr.getMessage());
            } else if (lr.getLevel().equals(Level.WARNING)) {
                logger.warn(lr.getMessage());
            } else if (lr.getLevel().equals(Level.INFO)) {
                logger.info(lr.getMessage());
            } else {
                logger.debug(lr.getMessage());
            }
        } catch (Exception e) {
            logger.error("Remote logging failed", e);
            return "Remote logging failed, check stack trace for details.";
        }
        return null;
    }
}
