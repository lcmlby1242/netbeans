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

package org.netbeans.modules.cnd.debugger.common2.debugger.breakpoints.types;

import javax.swing.JLabel;
import javax.swing.JTextField;
import org.netbeans.modules.cnd.debugger.common2.debugger.breakpoints.BreakpointPanel;
import org.netbeans.modules.cnd.debugger.common2.debugger.breakpoints.NativeBreakpoint;

import org.netbeans.modules.cnd.debugger.common2.utils.IpeUtils;

class FallbackBreakpointPanel extends BreakpointPanel {

    private FallbackBreakpoint fb;

    private JLabel eventspecLabel;
    private JTextField eventspecText;

    @Override
    public void seed(NativeBreakpoint breakpoint) {
	seedCommonComponents(breakpoint);
	fb = (FallbackBreakpoint) breakpoint;

	eventspecText.setText(fb.getEventspec());
    }

    /*
     * Constructors
     */

    public FallbackBreakpointPanel() {
	this(new FallbackBreakpoint(NativeBreakpoint.TOPLEVEL), false);
    }

    public FallbackBreakpointPanel(NativeBreakpoint b) {
	this((FallbackBreakpoint)b, true);
    }

    /** Creates new form FallbackBreakpointPanel */
    public FallbackBreakpointPanel(FallbackBreakpoint breakpoint,
				boolean customizing) {
	super(breakpoint, customizing);
	fb = breakpoint;

	initComponents();
	addCommonComponents(1);

	seed(breakpoint);

	// Arrange to revalidate on changes
	eventspecText.getDocument().addDocumentListener(this);
    }

    @Override
    public void setDescriptionEnabled(boolean enabled) {
	// eventspecLabel.setEnabled(false);
	eventspecText.setEnabled(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {
	panel_settings.setLayout(new java.awt.GridBagLayout());

	eventspecLabel = new JLabel();
	eventspecText = new JTextField();
	    java.awt.GridBagConstraints gbc;
	    eventspecLabel.setText(Catalog.get("LBL_Eventspec")); // NOI18N
	    eventspecLabel.setDisplayedMnemonic(
		Catalog.getMnemonic("MNEM_Eventspec"));		// NOI18N
	    eventspecLabel.setLabelFor(eventspecText);
	    gbc = new java.awt.GridBagConstraints();
	    gbc.ipadx = 5;
	    gbc.anchor = java.awt.GridBagConstraints.WEST;
	    panel_settings.add(eventspecLabel, gbc);

	    gbc = new java.awt.GridBagConstraints();
	    gbc.gridwidth = 3;
	    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
	    gbc.weightx = 1.0;
	    panel_settings.add(eventspecText, gbc);

	// a11y
	eventspecText.getAccessibleContext().setAccessibleDescription(
	    Catalog.get("ACSD_Eventspec") // NOI18N
	);
    }

    @Override
    protected void assignProperties() {
	fb.setEventspec(eventspecText.getText(), null);
    }
    
    @Override
    protected boolean propertiesAreValid() {
	// No good way to judge the validity of unanticipated engine eventspecs
	// But at least it shouldn't be null
	if (IpeUtils.isEmpty(eventspecText.getText()))
	    return false;
	return true;
    }
}