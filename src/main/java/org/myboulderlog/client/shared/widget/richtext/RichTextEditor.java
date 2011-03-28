package org.myboulderlog.client.shared.widget.richtext;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A sample toolbar for use with {@link RichTextArea}. It provides a simple UI
 * for all rich text formatting, dynamically displayed only for the available
 * functionality.
 */
public class RichTextEditor extends Composite implements LeafValueEditor<String> {

    interface Binder extends UiBinder<Widget, RichTextEditor> {
        Binder BINDER = GWT.create(Binder.class);
    }

    /**
     * This {@link ClientBundle} is used for all the button icons. Using a bundle
     * allows all of these images to be packed into a single image, which saves a
     * lot of HTTP requests, drastically improving startup time.
     */
    public interface Images extends ClientBundle {

        ImageResource bold();

        ImageResource createLink();

        ImageResource hr();

        ImageResource indent();

        ImageResource insertImage();

        ImageResource italic();

        ImageResource justifyCenter();

        ImageResource justifyLeft();

        ImageResource justifyRight();

        ImageResource ol();

        ImageResource outdent();

        ImageResource removeFormat();

        ImageResource removeLink();

        ImageResource strikeThrough();

        ImageResource subscript();

        ImageResource superscript();

        ImageResource ul();

        ImageResource underline();
    }

    /**
     * This {@link Constants} interface is used to make the toolbar's strings
     * internationalizable.
     */
    public interface Strings extends Constants {

        String black();

        String blue();

        String bold();

        String color();

        String createLink();

        String font();

        String green();

        String hr();

        String indent();

        String insertImage();

        String italic();

        String justifyCenter();

        String justifyLeft();

        String justifyRight();

        String large();

        String medium();

        String normal();

        String ol();

        String outdent();

        String red();

        String removeFormat();

        String removeLink();

        String size();

        String small();

        String strikeThrough();

        String subscript();

        String superscript();

        String ul();

        String underline();

        String white();

        String xlarge();

        String xsmall();

        String xxlarge();

        String xxsmall();

        String yellow();
    }

    /**
     * We use an inner EventHandler class to avoid exposing event methods on the
     * RichTextEditor itself.
     */
    private class EventHandler implements ClickHandler, ChangeHandler, KeyUpHandler {

        public void onChange(ChangeEvent event) {
            Widget sender = (Widget) event.getSource();

            if (sender == backColors) {
                formatter.setBackColor(backColors.getValue(backColors.getSelectedIndex()));
                backColors.setSelectedIndex(0);
            } else if (sender == foreColors) {
                formatter.setForeColor(foreColors.getValue(foreColors.getSelectedIndex()));
                foreColors.setSelectedIndex(0);
            } else if (sender == fonts) {
                formatter.setFontName(fonts.getValue(fonts.getSelectedIndex()));
                fonts.setSelectedIndex(0);
            } else if (sender == fontSizes) {
                formatter.setFontSize(fontSizesConstants[fontSizes.getSelectedIndex() - 1]);
                fontSizes.setSelectedIndex(0);
            }
        }

        public void onClick(ClickEvent event) {
            Widget sender = (Widget) event.getSource();

            if (sender == bold) {
                formatter.toggleBold();
            } else if (sender == italic) {
                formatter.toggleItalic();
            } else if (sender == underline) {
                formatter.toggleUnderline();
            } else if (sender == subscript) {
                formatter.toggleSubscript();
            } else if (sender == superscript) {
                formatter.toggleSuperscript();
            } else if (sender == strikethrough) {
                formatter.toggleStrikethrough();
            } else if (sender == indent) {
                formatter.rightIndent();
            } else if (sender == outdent) {
                formatter.leftIndent();
            } else if (sender == justifyLeft) {
                formatter.setJustification(RichTextArea.Justification.LEFT);
            } else if (sender == justifyCenter) {
                formatter.setJustification(RichTextArea.Justification.CENTER);
            } else if (sender == justifyRight) {
                formatter.setJustification(RichTextArea.Justification.RIGHT);
            } else if (sender == insertImage) {
                String url = Window.prompt("Enter an image URL:", "http://");
                if (url != null) {
                    formatter.insertImage(url);
                }
            } else if (sender == createLink) {
                String url = Window.prompt("Enter a link URL:", "http://");
                if (url != null) {
                    formatter.createLink(url);
                }
            } else if (sender == removeLink) {
                formatter.removeLink();
            } else if (sender == hr) {
                formatter.insertHorizontalRule();
            } else if (sender == ol) {
                formatter.insertOrderedList();
            } else if (sender == ul) {
                formatter.insertUnorderedList();
            } else if (sender == removeFormat) {
                formatter.removeFormat();
            } else if (sender == richTextArea) {
                // We use the RichTextArea's onKeyUp event to update the toolbar status.
                // This will catch any cases where the user moves the cursur using the
                // keyboard, or uses one of the browser's built-in keyboard shortcuts.
                updateStatus();
            }
        }

        public void onKeyUp(KeyUpEvent event) {
            Widget sender = (Widget) event.getSource();
            if (sender == richTextArea) {
                // We use the RichTextArea's onKeyUp event to update the descriptionEditor status.
                // This will catch any cases where the user moves the cursur using the
                // keyboard, or uses one of the browser's built-in keyboard shortcuts.
                updateStatus();
            }
        }
    }

    private static final RichTextArea.FontSize[] fontSizesConstants = new RichTextArea.FontSize[]{
            RichTextArea.FontSize.XX_SMALL,
            RichTextArea.FontSize.X_SMALL,
            RichTextArea.FontSize.SMALL,
            RichTextArea.FontSize.MEDIUM,
            RichTextArea.FontSize.LARGE,
            RichTextArea.FontSize.X_LARGE,
            RichTextArea.FontSize.XX_LARGE
    };

    private Strings strings = (Strings) GWT.create(Strings.class);
    private EventHandler handler = new EventHandler();
    private RichTextArea.Formatter formatter;

    @UiField
    RichTextArea richTextArea;

    @UiHandler("richTextArea")
    public void onKeyUpEvent(KeyUpEvent event) {
        handler.onKeyUp(event);
    }

    @UiHandler("richTextArea")
    public void onRichTextAreaClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    VerticalPanel container;

    @UiField
    HorizontalPanel firstToolbar;

    @UiField
    HorizontalPanel secondToolbar;

    @UiField
    ToggleButton bold;

    @UiHandler("bold")
    public void onBoldClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    ToggleButton italic;

    @UiHandler("italic")
    public void onItalicClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    ToggleButton underline;

    @UiHandler("underline")
    public void onUnderlineClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    ToggleButton subscript;

    @UiHandler("subscript")
    public void onSubcsriptClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public ToggleButton superscript;

    @UiHandler("superscript")
    public void onSuperscriptClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public ToggleButton strikethrough;

    @UiHandler("strikethrough")
    public void onStrikeThroughClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton indent;

    @UiHandler("indent")
    public void onIndentClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton outdent;

    @UiHandler("outdent")
    public void onOutdentClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton justifyLeft;

    @UiHandler("justifyLeft")
    public void onJustifyLeftClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton justifyCenter;

    @UiHandler("justifyCenter")
    public void onJustifyCenterClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton justifyRight;

    @UiHandler("justifyRight")
    public void onJustifyRigthClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton hr;

    @UiHandler("hr")
    public void onHrClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton ol;

    @UiHandler("ol")
    public void onOlClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton ul;

    @UiHandler("ul")
    public void onUlClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton insertImage;

    @UiHandler("insertImage")
    public void onInsertImageClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton createLink;

    @UiHandler("createLink")
    public void onCreateLinkClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton removeLink;

    @UiHandler("removeLink")
    public void onRemoveLinkClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public PushButton removeFormat;


    @UiHandler("removeFormat")
    public void onRemoveFormatClick(ClickEvent event) {
        handler.onClick(event);
    }

    @UiField
    public ListBox backColors;

    @UiHandler("backColors")
    public void onBackColorChange(ChangeEvent event) {
        handler.onChange(event);
    }

    @UiField
    public ListBox foreColors;

    @UiHandler("foreColors")
    public void onForeColorChange(ChangeEvent event) {
        handler.onChange(event);
    }

    @UiField
    public ListBox fonts;

    @UiHandler("fonts")
    public void onFontChange(ChangeEvent event) {
        handler.onChange(event);
    }

    @UiField
    public ListBox fontSizes;

    @UiHandler("fontSizes")
    public void onFontSizesChange(ChangeEvent event) {
        handler.onChange(event);
    }

    @UiConstructor
    public RichTextEditor() {
        initWidget(Binder.BINDER.createAndBindUi(this));
        this.formatter = richTextArea.getFormatter();

        createColorList(backColors, "Background");
        createColorList(foreColors, "Foreground");
        createFontList(fonts);
        createFontSizes(fontSizes);
    }

    private void createColorList(ListBox lb, String caption) {
        lb.addItem(caption);
        lb.addItem(strings.white(), "white");
        lb.addItem(strings.black(), "black");
        lb.addItem(strings.red(), "red");
        lb.addItem(strings.green(), "green");
        lb.addItem(strings.yellow(), "yellow");
        lb.addItem(strings.blue(), "blue");
    }

    private void createFontList(ListBox lb) {
        lb.addItem(strings.font(), "");
        lb.addItem(strings.normal(), "");
        lb.addItem("Times New Roman", "Times New Roman");
        lb.addItem("Arial", "Arial");
        lb.addItem("Courier New", "Courier New");
        lb.addItem("Georgia", "Georgia");
        lb.addItem("Trebuchet", "Trebuchet");
        lb.addItem("Verdana", "Verdana");
    }

    private void createFontSizes(ListBox lb) {
        lb.addItem(strings.size());
        lb.addItem(strings.xxsmall());
        lb.addItem(strings.xsmall());
        lb.addItem(strings.small());
        lb.addItem(strings.medium());
        lb.addItem(strings.large());
        lb.addItem(strings.xlarge());
        lb.addItem(strings.xxlarge());
    }

    /**
     * Updates the status of all the stateful buttons.
     */
    private void updateStatus() {
        bold.setDown(formatter.isBold());
        italic.setDown(formatter.isItalic());
        underline.setDown(formatter.isUnderlined());
        subscript.setDown(formatter.isSubscript());
        superscript.setDown(formatter.isSuperscript());
        strikethrough.setDown(formatter.isStrikethrough());
    }

    /**
     * Returns an Editor that is backed by the ValueBoxBase. The default
     * implementation returns {@link com.google.gwt.editor.ui.client.adapters.ValueBoxEditor#of(com.google.gwt.user.client.ui.ValueBoxBase)}. Subclasses
     * may override this method to provide custom error-handling when using the
     * Editor framework.
     */
    public RichTextEditor asEditor() {
        return this;
    }

    public String getValue() {
        return this.richTextArea.getHTML();
    }

    public void setValue(String text) {
        this.richTextArea.setHTML(text);
    }
}