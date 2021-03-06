package net.rrm.ehour.ui.report.panel.aggregate

import net.rrm.ehour.ui.common.report.AggregatedReportConfig
import net.rrm.ehour.ui.report.TreeReportModel
import net.rrm.ehour.ui.report.excel.ProjectReportExcel

class ProjectReportPanel(id: String, reportModel: TreeReportModel) extends AggregateReportPanel(id, reportModel,
  AggregatedReportConfig.AGGREGATE_PROJECT,
  AggregateReportChartGenerator.generateProjectReportChart,
  ProjectReportExcel.getInstance())
