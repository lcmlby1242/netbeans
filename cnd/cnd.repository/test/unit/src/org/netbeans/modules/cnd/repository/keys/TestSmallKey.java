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
package org.netbeans.modules.cnd.repository.keys;

import java.io.IOException;
import org.netbeans.modules.cnd.repository.spi.Key;
import org.netbeans.modules.cnd.repository.spi.KeyDataPresentation;
import org.netbeans.modules.cnd.repository.spi.RepositoryDataInput;

/**
 *
 */
public final class TestSmallKey extends TestAbstractKey {

    public TestSmallKey(RepositoryDataInput stream) throws IOException {
        super(stream);
    }

    public TestSmallKey(String key, int unitID) {
        super(key, unitID);
    }

    @Override
    public Key.Behavior getBehavior() {
        return Key.Behavior.Default;
    }

    @Override
    public boolean hasCache() {
        return false;
    }


    @Override
    protected short getHandler() {
        return TestBaseKeyFactory.SMALL_KEY_HANDLER;
    }
}