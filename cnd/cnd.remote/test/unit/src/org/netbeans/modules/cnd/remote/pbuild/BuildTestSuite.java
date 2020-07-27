/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.cnd.remote.pbuild;

import java.util.Collection;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.netbeans.modules.cnd.test.CndBaseTestSuite;

/**
 *
 */
public class BuildTestSuite extends CndBaseTestSuite {

    public static final String PLATFORMS_SECTION = "remote.platforms";
    public static final String DEFAULT_SECTION = "remote";

    public BuildTestSuite(Class testClass) {
        this(testClass.getName(), testClass);
    }

    // Why are tests just Test, not NativeExecutionBaseTestCase?
    // to allow add warnings (TestSuite.warning() returns test stub with warning)
    public BuildTestSuite(String name, Test... tests) {
        setName(name);
        for (Test test : tests) {
            addTest(test);
        }
    }

    // Why are tests just Test, not NativeExecutionBaseTestCase?
    // to allow add warnings (TestSuite.warning() returns test stub with warning)
    public BuildTestSuite(String name, Collection<Test> tests) {
        setName(name);
        for (Test test : tests) {
            addTest(test);
        }
    }

    public BuildTestSuite() {
        this("Remote Development", // NOI18N
             RfsGnuRemoteBuildTestCase.class,
             RfsSunStudioRemoteBuildTestCase.class,
             RemoteBuildSamplesTestCase.class,
             RemoteBuildMakefileTestCase.class,
             RemoteBuildLinksTestCase.class);
    }

    private BuildTestSuite(String name, Class... testClasses) {
        super(name, PLATFORMS_SECTION, testClasses);
    }

    public static Test suite() {
        TestSuite suite = new BuildTestSuite();
        return suite;
    }
}