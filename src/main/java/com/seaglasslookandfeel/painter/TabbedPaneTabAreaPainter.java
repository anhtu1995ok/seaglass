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
 * $Id$
 */
package com.seaglasslookandfeel.painter;

import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

import com.seaglasslookandfeel.painter.AbstractRegionPainter.PaintContext.CacheMode;
import com.seaglasslookandfeel.painter.util.PaintUtil;
import com.seaglasslookandfeel.painter.util.PaintUtil.ButtonType;
import com.seaglasslookandfeel.ui.SeaGlassTabbedPaneUI;

/**
 * Sea Glass TabbedPaneTabAreaPainter. Does nothing.
 */
public final class TabbedPaneTabAreaPainter extends AbstractRegionPainter {
    public static enum Which {
        BACKGROUND_ENABLED_TOP, BACKGROUND_ENABLED_LEFT, BACKGROUND_ENABLED_BOTTOM, BACKGROUND_ENABLED_RIGHT,

        BACKGROUND_DISABLED_TOP, BACKGROUND_DISABLED_LEFT, BACKGROUND_DISABLED_BOTTOM, BACKGROUND_DISABLED_RIGHT,
    }

    public Which         state;
    private PaintContext ctx;
    private ButtonType   type;

    public TabbedPaneTabAreaPainter(Which state) {
        super();
        this.state = state;
        this.ctx = new PaintContext(CacheMode.NO_CACHING);

        switch (state) {
        case BACKGROUND_DISABLED_TOP:
        case BACKGROUND_DISABLED_LEFT:
        case BACKGROUND_DISABLED_BOTTOM:
        case BACKGROUND_DISABLED_RIGHT:
            type = ButtonType.DISABLED;
            break;
        case BACKGROUND_ENABLED_TOP:
        case BACKGROUND_ENABLED_LEFT:
        case BACKGROUND_ENABLED_BOTTOM:
        case BACKGROUND_ENABLED_RIGHT:
            type = ButtonType.ENABLED;
            break;
        }
    }

    protected void doPaint(Graphics2D g, JComponent c, int width, int height, Object[] extendedCacheKeys) {
        JTabbedPane tabPane = (JTabbedPane) c;
        SeaGlassTabbedPaneUI ui = (SeaGlassTabbedPaneUI) tabPane.getUI();
        int orientation = tabPane.getTabPlacement();
        Insets insets = tabPane.getInsets();

        if (orientation == JTabbedPane.LEFT) {
            int offset = ui.calculateMaxTabWidth(orientation) + insets.left;
            paintVerticalLine(g, c, offset / 2 + 3, 0, width, height);
        } else if (orientation == JTabbedPane.RIGHT) {
            int offset = ui.calculateMaxTabWidth(orientation);
            paintVerticalLine(g, c, offset / 2 + 3, 0, width, height);
        } else if (orientation == JTabbedPane.BOTTOM) {
            int offset = ui.calculateMaxTabHeight(orientation);
            paintHorizontalLine(g, c, 0, offset / 2 + 3, width, height);
        } else {
            int offset = ui.calculateMaxTabHeight(orientation) / 2 + insets.top;
            paintHorizontalLine(g, c, 0, offset + 3, width, height);
        }
    }

    protected final PaintContext getPaintContext() {
        return ctx;
    }

    private void paintHorizontalLine(Graphics2D g, JComponent c, int x, int y, int width, int height) {
        g.setPaint(PaintUtil.getTabbedPaneTabAreaHorizontalPaint(x, y - 1, width, 4));
        g.fillRect(x, y - 1, width, 4);
        g.setPaint(PaintUtil.getTabbedPaneTabAreaBackgroundColor(type));
        g.drawLine(x, y, x + width - 1, y);
    }

    private void paintVerticalLine(Graphics2D g, JComponent c, int x, int y, int width, int height) {
        g.setPaint(PaintUtil.getTabbedPaneTabAreaVerticalPaint(x - 1, y, 3, height));
        g.fillRect(x - 1, y, 3, height);
        g.setPaint(PaintUtil.getTabbedPaneTabAreaBackgroundColor(type));
        g.drawLine(x, y, x, y + height - 1);
    }
}
