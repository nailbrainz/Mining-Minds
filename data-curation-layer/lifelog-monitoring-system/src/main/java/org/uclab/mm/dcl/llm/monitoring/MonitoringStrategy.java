/**
 * 
 * Copyright [2016] [Bilal Ali]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under 
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 *  ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */
package org.uclab.mm.dcl.llm.monitoring;

import java.sql.Connection;
import java.sql.SQLException;
import org.uclab.mm.dcl.llm.objectmodel.FoodLog;

/**
 * This is an interface to define the strategy for the monitoring of the
 * activities which may be either physical or nutrition.
 *
 * @author Rizvi
 */
public interface MonitoringStrategy{

  /**
   * This method is used to monitor the different kinds of physical activities.
   *
   * @param dbConn
   * @param objMonitoringEvent
   */
  public void doMonitor(Connection dbConn, String objMonitoringEvent) throws SQLException;

  /**
   * This method is used to monitor the Food nutrition.
   *
   * @param objFoodLog
   */
  public void doMonitor(FoodLog objFoodLog);

}
