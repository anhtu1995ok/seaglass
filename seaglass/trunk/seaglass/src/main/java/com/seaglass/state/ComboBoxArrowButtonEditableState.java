/*
 * Copyright (c) 2009 Kathryn Huxtable and Kenneth Orr.
 *
 * This file is part of the SeaGlass Pluggable Look and Feel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * $Id: SeaGlassLookAndFeel.java 183 2009-10-07 01:02:30Z kathryn@kathrynhuxtable.org $
 */
package com.seaglass.state;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.seaglass.State;

/**
 */
public class ComboBoxArrowButtonEditableState extends State {
    public ComboBoxArrowButtonEditableState() {
        super("Editable");
    }

    protected boolean isInState(JComponent c) {
        Component parent = c.getParent();
        return parent instanceof JComboBox && ((JComboBox) parent).isEditable();
    }
}
