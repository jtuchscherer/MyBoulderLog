/*
 * Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.myboulderlog.client.shared.widget.richtext;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * A simple decorator to display leaf widgets with an error message.
 * <p>
 * <h3>Use in UiBinder Templates</h3>
 * <p>
 * The decorator may have exactly one ValueBoxBase added though an
 * <code>&lt;e:valuebox></code> child tag.
 * <p>
 * For example:
 * <pre>
 * &#64;UiField
 * ValueBoxEditorDecorator&lt;String&gt; name;
 * </pre>
 * <pre>
 * &lt;e:ValueBoxEditorDecorator ui:field='name'>
 *   &lt;e:valuebox>
 *     &lt;g:TextBox />
 *   &lt;/e:valuebox>
 * &lt;/e:ValueBoxEditorDecorator>
 * </pre>
 * 
 * @param <T> the type of data being edited
 */
public class RichTextAreaEditorDecorator<T> extends Composite implements
    HasEditorErrors<T>, IsEditor<RichTextEditor> {
  interface Binder extends UiBinder<Widget, RichTextAreaEditorDecorator<?>> {
    Binder BINDER = GWT.create(Binder.class);
  }

  @UiField
  SimplePanel contents;

  @UiField
  DivElement errorLabel;

  private RichTextEditor editor;

  /**
   * Constructs a ValueBoxEditorDecorator.
   */
  @UiConstructor
  public RichTextAreaEditorDecorator() {
    initWidget(Binder.BINDER.createAndBindUi(this));
  }

  /**
   * Constructs a RichTextAreaEditorDecorator using a {@link ValueBoxBase}
   * widget and a {@link RichTextEditor} editor.
   * 
   * @param widget the widget
   * @param editor the editor
   */
  public RichTextAreaEditorDecorator(
          ValueBoxBase<T> widget, RichTextEditor editor) {
    this();
    contents.add(widget);
    this.editor = editor;
  }

  /**
   * Returns the associated {@link RichTextEditor}.
   * 
   * @return a {@link RichTextEditor} instance
   * @see #setEditor(RichTextEditor)
   */
  public RichTextEditor asEditor() {
    return editor;
  }

  /**
   * Sets the associated {@link RichTextEditor}.
   * 
   * @param editor a {@link RichTextEditor} instance
   * @see #asEditor()
   */
  public void setEditor(RichTextEditor editor) {
    this.editor = editor;
  }

  /**
   * Set the widget that the EditorPanel will display. This method will
   * automatically call {@link #setEditor}.
   * 
   * @param widget a {@link ValueBoxBase} widget
   */
  @UiChild(limit = 1, tagname = "richtexteditor")
  public void setRichTextEditor(RichTextEditor widget) {
    contents.add(widget);
    setEditor(widget.asEditor());
  }

  /**
   * The default implementation will display, but not consume, received errors
   * whose {@link EditorError#getEditor() getEditor()} method returns the Editor
   * passed into {@link #setEditor}.
   * 
   * @param errors a List of {@link EditorError} instances
   */
  public void showErrors(List<EditorError> errors) {
    StringBuilder sb = new StringBuilder();
    for (EditorError error : errors) {
      if (error.getEditor().equals(editor)) {
        sb.append("\n").append(error.getMessage());
      }
    }

    if (sb.length() == 0) {
      errorLabel.setInnerText("");
      errorLabel.getStyle().setDisplay(Display.NONE);
      return;
    }

    errorLabel.setInnerText(sb.substring(1));
    errorLabel.getStyle().setDisplay(Display.INLINE_BLOCK);
  }
}
