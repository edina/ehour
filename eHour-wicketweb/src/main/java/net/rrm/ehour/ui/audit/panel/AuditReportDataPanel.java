package net.rrm.ehour.ui.audit.panel;

import java.util.Date;

import net.rrm.ehour.audit.service.dto.AuditReportRequest;
import net.rrm.ehour.config.EhourConfig;
import net.rrm.ehour.domain.Audit;
import net.rrm.ehour.ui.audit.model.AuditReportDataProvider;
import net.rrm.ehour.ui.common.border.GreyBlueRoundedBorder;
import net.rrm.ehour.ui.common.component.datatable.AjaxDataTable;
import net.rrm.ehour.ui.common.model.DateModel;
import net.rrm.ehour.ui.common.panel.AbstractAjaxPanel;
import net.rrm.ehour.ui.common.session.EhourWebSession;

import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.markup.html.resources.StyleSheetReference;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class AuditReportDataPanel extends AbstractAjaxPanel
{
	private static final long serialVersionUID = -2380789244030608920L;

	public AuditReportDataPanel(String id, IModel model)
	{
		super(id, model);

		setOutputMarkupId(true);
		
		addComponents(model);
	}
	
	/**
	 * Add components to the page
	 */
	private void addComponents(IModel model)
	{
		Border greyBorder = new GreyBlueRoundedBorder("border");
		add(greyBorder);
		
		greyBorder.add(getPagingDataView(model));
		
		add(new StyleSheetReference("auditStyle", new CompressedResourceReference(AuditReportDataPanel.class, "style/auditStyle.css")));

	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private WebMarkupContainer getPagingDataView(IModel model)
	{
		final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
		dataContainer.setOutputMarkupId(true);
		final EhourConfig config = EhourWebSession.getSession().getEhourConfig();
        
		IColumn[] columns = new IColumn[4];
        columns[0] = new DateColumn(new Model("Date"), config);
        columns[1] = new PropertyColumn(new Model("Last Name"), "userFullName", "userFullName");
        columns[2] = new PropertyColumn(new Model("Action"), "action", "action");
        columns[3] = new PropertyColumn(new Model("Type"), "auditActionType.value", "auditActionType.value");

        AjaxDataTable table = new AjaxDataTable("data", columns, new AuditReportDataProvider((AuditReportRequest)model.getObject()), 20);
		dataContainer.add(table);
		
		return dataContainer;
	}
	
	private class DateColumn extends AbstractColumn
	{
		private static final long serialVersionUID = -5517077439980001335L;
		private EhourConfig config;
		
		public DateColumn(IModel displayModel, EhourConfig config)
		{
			super(displayModel);
				
			this.config = config;
		}

		/*
		 * (non-Javadoc)
		 * @see org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator#populateItem(org.apache.wicket.markup.repeater.Item, java.lang.String, org.apache.wicket.model.IModel)
		 */
		public void populateItem(Item item, String componentId, IModel model)
		{
			System.out.println(model.getObject());
			
			Date date = ((Audit)model.getObject()).getDate();
			
			item.add(new Label(componentId, new DateModel(date, config)));
		}
	}
}