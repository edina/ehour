/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package net.rrm.ehour.ui.timesheet.page;

import net.rrm.ehour.domain.User;
import net.rrm.ehour.timesheet.dto.TimesheetOverview;
import net.rrm.ehour.timesheet.service.IOverviewTimesheet;
import net.rrm.ehour.ui.common.BaseSpringWebAppTester;
import net.rrm.ehour.ui.common.MockExpectations;
import org.junit.Test;

import java.util.Calendar;

import static org.easymock.EasyMock.*;


/**
 * Overview page test
 **/
public class MonthOverviewPageTest extends BaseSpringWebAppTester
{
	@Test
	public void testOverviewPageRender()
	{
		IOverviewTimesheet overviewTimesheet = createMock(IOverviewTimesheet.class);
		getMockContext().putBean(overviewTimesheet);
		
		MockExpectations.navCalendarEasyMock(overviewTimesheet, getWebApp());

		TimesheetOverview overview = new TimesheetOverview();
		
		expect(overviewTimesheet.getTimesheetOverview((User)notNull(), (Calendar)notNull()))
				.andReturn(overview);					

		replay(overviewTimesheet);
		
		getTester().startPage(MonthOverviewPage.class);
		getTester().assertRenderedPage(MonthOverviewPage.class);
		getTester().assertNoErrorMessage();
		
		verify(overviewTimesheet);
	}
}
