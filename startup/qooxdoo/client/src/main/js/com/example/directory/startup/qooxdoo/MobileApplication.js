/*
 * Copyright (c) 2005-2016 Vincent Vandenschrick. All rights reserved.
 *
 *  This file is part of the Jspresso framework.
 *
 *  Jspresso is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jspresso is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Jspresso.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * This is the main mobile application class
 */
qx.Class.define("com.example.directory.startup.qooxdoo.MobileApplication",
{
  extend : org.jspresso.framework.application.frontend.MobileApplication,



  /*
  *****************************************************************************
     MEMBERS
  *****************************************************************************
  */

  members :
  {
    start : function() {
      var remoteController;
      if (qx.core.Environment.get("qx.debug")) {
        remoteController = new org.jspresso.framework.io.Rpc(
            "http://localhost:9080/directory-webapp/.qxrpc",
            "com.example.directory.startup.remote.RemoteMobileApplicationStartup"
        );
        //remoteController.setCrossDomain(true);
      } else {
        remoteController = new org.jspresso.framework.io.Rpc(
            org.jspresso.framework.io.Rpc.makeServerURL(),
            "com.example.directory.startup.remote.RemoteMobileApplicationStartup"
        );
      }
      remoteController.setTimeout(600000);

      this.startController(remoteController);
    }
  }
});
