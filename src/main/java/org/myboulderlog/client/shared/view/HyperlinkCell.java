package org.myboulderlog.client.shared.view;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Hyperlink;


/**
 * {@link Cell} that wraps a {@link Hyperlink}
 * WARNING: make sure the contents of your Hyperlink really are safe from XSS!
 *
 * @author turbomanage
 */
public class HyperlinkCell extends AbstractCell<Hyperlink> {

    @Override
    public void render(
            com.google.gwt.cell.client.Cell.Context context, Hyperlink h, SafeHtmlBuilder sb)
    {
        sb.append(SafeHtmlUtils.fromTrustedString(h.toString()));
    }

}
