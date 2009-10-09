package net.rrm.ehour.ui.admin.project.panel;

import java.util.List;

import net.rrm.ehour.domain.Project;
import net.rrm.ehour.domain.ProjectAssignment;
import net.rrm.ehour.project.service.ProjectAssignmentService;
import net.rrm.ehour.ui.common.border.GreySquaredRoundedBorder;
import net.rrm.ehour.ui.common.panel.AbstractFormSubmittingPanel;
import net.rrm.ehour.ui.common.util.WebGeo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ModifyProjectUsersPanel extends AbstractFormSubmittingPanel
{
	private static final long serialVersionUID = -2500957894019585211L;

	@SpringBean
	private ProjectAssignmentService assignmentService;
	
	@SuppressWarnings("serial")
	public ModifyProjectUsersPanel(String id, Project project)
	{
		super(id);
		
		Border border = new GreySquaredRoundedBorder("border", WebGeo.W_CONTENT_ADMIN_TAB_WIDE);
		add(border);

		final List<ProjectAssignment> assignments = assignmentService.getProjectAssignments(project, true);
		
		ListCurrentProjectUsersPanel currentProjectUsersPanel = new ListCurrentProjectUsersPanel("currentUsers", assignments);
		border.add(currentProjectUsersPanel);
		
		AjaxLink link = new AjaxLink("submit")
		{
			@Override
			public void onClick(AjaxRequestTarget target)
			{
				for (ProjectAssignment projectAssignment : assignments)
				{
					assignmentService.updateProjectAssignment(projectAssignment);
				}
			}
		};
		
		border.add(link);	
	}

}
