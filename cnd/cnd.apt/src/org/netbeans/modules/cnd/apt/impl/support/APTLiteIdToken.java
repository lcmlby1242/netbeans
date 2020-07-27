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
package org.netbeans.modules.cnd.apt.impl.support;

import org.netbeans.modules.cnd.apt.support.APTTokenAbstact;
import org.netbeans.modules.cnd.apt.support.APTTokenTypes;
import org.netbeans.modules.cnd.utils.cache.TextCache;
import org.openide.util.CharSequences;

/**
 *
 */
public final class APTLiteIdToken extends APTTokenAbstact {
    private static final int COL_BITS  = 10;
    private static final int MAX_COL   = (1<<COL_BITS) - 1;
    private static final int LINE_BITS = 22;
    private static final int MAX_LINE  = (1<<LINE_BITS) - 1;
//    private static final int TYPE_BITS = 6;
//    private static final int MAX_TYPE  = (1<<TYPE_BITS) - 1;
    private final int offset;
    private final int columnLineType;
    private CharSequence text = null;

    public static boolean isApplicable(int type, int offset, int column, int line) {
        if (type == APTTokenTypes.IDENT && line <= MAX_LINE && column <= MAX_COL) {
            return true;
        }
        return false;
    }

    /**
     * Creates a new instance of APTConstTextToken
     */
    public APTLiteIdToken(int offset, int column, int line) {
        this.offset = offset;
        columnLineType = ((column & MAX_COL)<<LINE_BITS) + (line & MAX_LINE);
        assert column == getColumn();
        assert line == getLine();
    }
    public APTLiteIdToken(int offset, int column, int line, CharSequence text) {
        this.offset = offset;
        columnLineType = ((column & MAX_COL)<<LINE_BITS) + (line & MAX_LINE);
        assert text != null;
        assert CharSequences.isCompact(text);
        this.text = TextCache.getManager().getString(text);
        assert column == getColumn();
        assert line == getLine();
    }

    @Override
    public final CharSequence getTextID() {
        return this.text;
    }

    @Override
    public final void setTextID(CharSequence textID) {
        this.text = TextCache.getManager().getString(textID);
    }

    @Override
    public final String getText() {
        if(this.text != null) {
            return text.toString();
        } else {
            return "";
        }
    }

    @Override
    public final void setText(String t) {
        text = TextCache.getManager().getString(t);
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public void setOffset(int o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getType() {
        return APTTokenTypes.IDENT;
    }

    @Override
    public void setType(int t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getColumn() {
        return (columnLineType>>LINE_BITS) & MAX_COL;
    }

    @Override
    public void setColumn(int c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getLine() {
        return columnLineType & MAX_LINE;
    }

    @Override
    public void setLine(int l) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getEndOffset() {
        return getOffset() + getTextID().length();
    }

    @Override
    public int getEndLine() {
        return getLine();
    }

    @Override
    public int getEndColumn() {
        return getColumn() + getTextID().length();
    }
}