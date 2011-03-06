package org.myboulderlog.client.admin.view;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.inject.Inject;
import org.myboulderlog.client.admin.mapper.AdminPlaceHistoryMapper;
import org.myboulderlog.client.admin.place.GymPreviewPlace;
import org.myboulderlog.client.shared.view.HyperlinkCell;
import org.myboulderlog.shared.proxy.GymProxy;
import org.myboulderlog.shared.proxy.GymProxyKeyProvider;
import org.myboulderlog.shared.request.AdminRequestFactory;

public class GymListViewImpl extends Composite implements GymListView {

    private GymListView.Presenter presenter;
    private Column<GymProxy, String> nameColumn;
    private GymProxyKeyProvider gymProxyKeyProvider;
    private CreateGymDialogBox createGymDialogBox;

    public void setPresenter(GymListView.Presenter presenter) {
        this.presenter = presenter;
    }

    public Column<GymProxy, String> getNameColumn() {
        return nameColumn;
    }

    public HasData<GymProxy> getDataTable() {
        return cellTable;
    }

    interface WidgetUiBinder extends UiBinder<Widget, GymListViewImpl> {
    }

    private static WidgetUiBinder uiBinder = GWT.create(WidgetUiBinder.class);


    @UiField
    HTMLPanel container;

    /**
     * The main CellTable.
     */
    @UiField(provided = true)
    CellTable<GymProxy> cellTable;
    /**
     * The pager used to change the range of data.
     */
//    @UiField(provided = true)
//    SimplePager pager;

    @UiField
    Button createNewButton;

    @UiHandler("createNewButton")
    void onRoutesClick(ClickEvent event) {
        createGymDialogBox.show();
    }

    /**
     * Initialize this example.
     */
    @Inject
    public GymListViewImpl(GymProxyKeyProvider gymProxyKeyProvider, CreateGymDialogBox createGymDialogBox) {
        this.gymProxyKeyProvider = gymProxyKeyProvider;
        this.createGymDialogBox = createGymDialogBox;
        // Create a CellTable.

        // Set a key provider that provides a unique key for each contact. If key is
        // used to identify contacts when fields (such as the name and address)
        // change.
        cellTable = new CellTable<GymProxy>(this.gymProxyKeyProvider);
        cellTable.setWidth("100%", true);

//    // Attach a column sort handler to the ListDataProvider to sort the list.
//    ColumnSortEvent.ListHandler<GymProxy> sortHandler = new ColumnSortEvent.ListHandler<GymProxy>(
//        ContactDatabase.get().getDataProvider().getList());
//    cellTable.addColumnSortHandler(sortHandler);

//    // Create a Pager to control the table.
//    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
//    pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
//    pager.setDisplay(cellTable);
//
//    // Add a selection model so we can select cells.
//    final SelectionModel<GymProxy> selectionModel = new MultiSelectionModel<GymProxy>(
//        ContactDatabase.GymProxy.KEY_PROVIDER);
//    cellTable.setSelectionModel(selectionModel,
//        DefaultSelectionEventManager.<GymProxy> createCheckboxManager());

        // Initialize the columns.
//    initTableColumns(selectionModel, sortHandler);
        initTableColumns();
        // Add the CellList to the adapter in the database.
//    ContactDatabase.get().addDataDisplay(cellTable);

        // Create the UiBinder.
        initWidget(uiBinder.createAndBindUi(this));

    }

    private void initTableColumns() {
        // name
        nameColumn = new Column<GymProxy, String>(new EditTextCell()) {
            @Override
            public String getValue(GymProxy object) {
                return object.getName();
            }
        };
        nameColumn.setFieldUpdater(new FieldUpdater<GymProxy, String>() {
            public void update(int index, GymProxy object, String value) {
                object.setName(value);
            }
        }
        );
        ActionCell<GymProxy> actionCell = new ActionCell<GymProxy>("Delete", new ActionCell.Delegate<GymProxy>() {
            public void execute(GymProxy object) {
                presenter.removeGym(object);
            }
        }
        );
        Column<GymProxy, GymProxy> delColumn = new Column<GymProxy, GymProxy>(actionCell) {
            @Override
            public GymProxy getValue(GymProxy object) {
                return object;
            }
        };
        Column<GymProxy, Hyperlink> previewLinkColumn = new Column<GymProxy, Hyperlink>(new HyperlinkCell()) {
            @Override
            public Hyperlink getValue(GymProxy gym) {
                String historyToken = presenter.getHistoryToken(gym);
                return new Hyperlink(gym.getName(), historyToken);
            }
        };
        cellTable.addColumn(nameColumn, "Name");
        cellTable.setColumnWidth(nameColumn, 60, Style.Unit.PCT);
        cellTable.addColumn(delColumn, "Delete");
        cellTable.setColumnWidth(delColumn, 20, Style.Unit.PCT);
        cellTable.addColumn(previewLinkColumn,"Preview");
        cellTable.setColumnWidth(previewLinkColumn, 20, Style.Unit.PCT);
    }

    /**
     * Add the columns to the table.
     */
//  private void initTableColumns(
//      final SelectionModel<GymProxy> selectionModel,
//      ColumnSortEvent.ListHandler<GymProxy> sortHandler) {
//    // Checkbox column. This table will uses a checkbox column for selection.
//    // Alternatively, you can call cellTable.setSelectionEnabled(true) to enable
//    // mouse selection.
//    Column<GymProxy, Boolean> checkColumn = new Column<GymProxy, Boolean>(
//        new CheckboxCell(true, false)) {
//      @Override
//      public Boolean getValue(GymProxy object) {
//        // Get the value from the selection model.
//        return selectionModel.isSelected(object);
//      }
//    };
//    cellTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
//    cellTable.setColumnWidth(checkColumn, 40, Style.Unit.PX);
//
//    // First name.
//    Column<GymProxy, String> firstNameColumn = new Column<GymProxy, String>(
//        new EditTextCell()) {
//      @Override
//      public String getValue(GymProxy object) {
//        return object.getFirstName();
//      }
//    };
//    firstNameColumn.setSortable(true);
//    sortHandler.setComparator(firstNameColumn, new Comparator<GymProxy>() {
//      public int compare(GymProxy o1, GymProxy o2) {
//        return o1.getFirstName().compareTo(o2.getFirstName());
//      }
//    });
//    cellTable.addColumn(firstNameColumn, constants.cwCellTableColumnFirstName());
//    firstNameColumn.setFieldUpdater(new FieldUpdater<GymProxy, String>() {
//      public void update(int index, GymProxy object, String value) {
//        // Called when the user changes the value.
//        object.setFirstName(value);
//        ContactDatabase.get().refreshDisplays();
//      }
//    });
//    cellTable.setColumnWidth(firstNameColumn, 20, Unit.PCT);
//
//    // Last name.
//    Column<GymProxy, String> lastNameColumn = new Column<GymProxy, String>(
//        new EditTextCell()) {
//      @Override
//      public String getValue(GymProxy object) {
//        return object.getLastName();
//      }
//    };
//    lastNameColumn.setSortable(true);
//    sortHandler.setComparator(lastNameColumn, new Comparator<GymProxy>() {
//      public int compare(GymProxy o1, GymProxy o2) {
//        return o1.getLastName().compareTo(o2.getLastName());
//      }
//    });
//    cellTable.addColumn(lastNameColumn, constants.cwCellTableColumnLastName());
//    lastNameColumn.setFieldUpdater(new FieldUpdater<GymProxy, String>() {
//      public void update(int index, GymProxy object, String value) {
//        // Called when the user changes the value.
//        object.setLastName(value);
//        ContactDatabase.get().refreshDisplays();
//      }
//    });
//    cellTable.setColumnWidth(lastNameColumn, 20, Unit.PCT);
//
//    // Category.
//    final Category[] categories = ContactDatabase.get().queryCategories();
//    List<String> categoryNames = new ArrayList<String>();
//    for (Category category : categories) {
//      categoryNames.add(category.getDisplayName());
//    }
//    SelectionCell categoryCell = new SelectionCell(categoryNames);
//    Column<GymProxy, String> categoryColumn = new Column<GymProxy, String>(
//        categoryCell) {
//      @Override
//      public String getValue(GymProxy object) {
//        return object.getCategory().getDisplayName();
//      }
//    };
//    cellTable.addColumn(categoryColumn, constants.cwCellTableColumnCategory());
//    categoryColumn.setFieldUpdater(new FieldUpdater<GymProxy, String>() {
//      public void update(int index, GymProxy object, String value) {
//        for (Category category : categories) {
//          if (category.getDisplayName().equals(value)) {
//            object.setCategory(category);
//          }
//        }
//        ContactDatabase.get().refreshDisplays();
//      }
//    });
//    cellTable.setColumnWidth(categoryColumn, 130, Style.Unit.PX);
//
//    // Address.
//    Column<GymProxy, String> addressColumn = new Column<GymProxy, String>(
//        new TextCell()) {
//      @Override
//      public String getValue(GymProxy object) {
//        return object.getAddress();
//      }
//    };
//    addressColumn.setSortable(true);
//    sortHandler.setComparator(addressColumn, new Comparator<GymProxy>() {
//      public int compare(GymProxy o1, GymProxy o2) {
//        return o1.getAddress().compareTo(o2.getAddress());
//      }
//    });
//    cellTable.addColumn(addressColumn, constants.cwCellTableColumnAddress());
//    cellTable.setColumnWidth(addressColumn, 60, Style.Unit.PCT);
//  }

}
