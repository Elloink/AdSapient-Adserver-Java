/*
 * Copyright (C) 2003 Erik Swenson - erik@oreports.com
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 *  
 */

package org.efs.openreports.actions.admin;

import java.util.*;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

import org.apache.log4j.Logger;

import org.efs.openreports.ORStatics;
import org.efs.openreports.engine.ChartReportEngine;
import org.efs.openreports.objects.ReportChart;
import org.efs.openreports.objects.ReportParameter;
import org.efs.openreports.objects.ReportUser;
import org.efs.openreports.objects.chart.ChartValue;
import org.efs.openreports.providers.*;
import org.efs.openreports.util.ORUtil;

public class EditChartAction extends ActionSupport
		implements
			DataSourceProviderAware,
			ChartProviderAware,
			ParameterProviderAware,
			ReportProviderAware,
			DirectoryProviderAware,
			PropertiesProviderAware
{
	protected static Logger log =
		Logger.getLogger(EditChartAction.class);

	private String command;
	
	private boolean submitOk;
	private boolean submitValidate;

	private int id;
	private String name;
	private String description;
	private String query;
	private int dataSourceId = Integer.MIN_VALUE;	
	private int reportId = Integer.MIN_VALUE;
	private int chartType;
	private int width = 600;
	private int height = 400;
	private String xAxisLabel;
	private String yAxisLabel;
	private int orientation;
	private boolean showLegend;
	private boolean showTitle;
	private boolean showValues;

	private ReportChart reportChart;		
	private ChartValue[] chartValues;

	private DataSourceProvider dataSourceProvider;
	private ChartProvider chartProvider;
	private ParameterProvider parameterProvider;
	private ReportProvider reportProvider;	
	private PropertiesProvider propertiesProvider;
	private DirectoryProvider directoryProvider;

	public String execute()
	{
		try
		{
			if (command.equals("edit"))
			{
				reportChart =
					chartProvider.getReportChart(new Integer(id));
			}
			else
			{
				reportChart = new ReportChart();
			}

			if (command.equals("edit") && !submitOk && !submitValidate)
			{
				name = reportChart.getName();
				description = reportChart.getDescription();
				query = reportChart.getQuery();				
				chartType = reportChart.getChartType();
				width = reportChart.getWidth();
				height = reportChart.getHeight();
				xAxisLabel = reportChart.getXAxisLabel();
				yAxisLabel = reportChart.getYAxisLabel();
				orientation = reportChart.getPlotOrientation();
				showLegend = reportChart.isShowLegend();
				showTitle = reportChart.isShowTitle();
				showValues = reportChart.isShowValues();
				id = reportChart.getId().intValue();
				
				if (reportChart.getDataSource() != null)
				{
					dataSourceId =
						reportChart.getDataSource().getId().intValue();
				}	
				
				if (reportChart.getDrillDownReport() != null)
				{
					reportId = reportChart.getDrillDownReport().getId().intValue();
				}
				
			}

			if (!submitOk && !submitValidate)
				return INPUT;

			reportChart.setName(name);
			reportChart.setDescription(description);
			reportChart.setQuery(query);		
			reportChart.setChartType(chartType);
			reportChart.setWidth(width);
			reportChart.setHeight(height);
			reportChart.setXAxisLabel(xAxisLabel);
			reportChart.setYAxisLabel(yAxisLabel);
			reportChart.setShowLegend(new Boolean(showLegend));
			reportChart.setShowTitle(new Boolean(showTitle));
			reportChart.setShowValues(new Boolean(showValues));
			reportChart.setPlotOrientation(new Integer(orientation));
			
			if (dataSourceId != -1) reportChart.setDataSource(dataSourceProvider
					.getDataSource(new Integer(dataSourceId)));
			
			if (reportId != -1)
			{
				reportChart.setDrillDownReport(reportProvider.getReport(new Integer(reportId)));	
			}
			else
			{
				reportChart.setDrillDownReport(null);
			}					
			
			if (submitValidate)
			{
				Map map = new HashMap();
				if (query.toUpperCase().indexOf("$P") > -1)
				{
					ReportUser reportUser = (ReportUser) ActionContext.getContext().getSession().get(ORStatics.REPORT_USER);
					map = ORUtil.buildQueryParameterMap(reportUser, query, parameterProvider);
				}
				
				ChartReportEngine chartReportEngine = new ChartReportEngine(
						dataSourceProvider, directoryProvider, propertiesProvider);				
				
				chartValues = chartReportEngine.getChartValues(reportChart, map);

				return INPUT;
			}			

			if (command.equals("edit"))
			{
				chartProvider.updateReportChart(reportChart);
			}

			if (command.equals("add"))
			{
				chartProvider.insertReportChart(reportChart);
			}

			return SUCCESS;
		}
		catch (Exception e)
		{
			addActionError(e.getMessage());
			return INPUT;
		}
	}

	public String getCommand()
	{
		return command;
	}

	public void setCommand(String command)
	{
		this.command = command;
	}	
	
	public void setSubmitOk(String submitOk)
	{
		if (submitOk != null) this.submitOk = true;
	}
	
	public void setSubmitValidate(String submitValidate)
	{
		if (submitValidate != null) this.submitValidate = true;
	}
	
	public int getDataSourceId()
	{
		return dataSourceId;
	}

	public String getName()
	{
		return name;
	}

	public void setDataSourceId(int dataSourceId)
	{
		this.dataSourceId = dataSourceId;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List getDataSources()
	{
		try
		{
			return dataSourceProvider.getDataSources();
		}
		catch (Exception e)
		{
			addActionError(e.getMessage());
			return null;
		}
	}
	
	public List getReports()
	{	
		try
		{
			return reportProvider.getReports();		
		}
		catch (Exception e)
		{
			addActionError(e.getMessage());
			return null;
		}		
	}

	public String[] getTypes()
	{
		return ReportParameter.TYPES;
	}

	public String[] getClassNames()
	{
		return ReportParameter.CLASS_NAMES;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}	

	public void setDataSourceProvider(DataSourceProvider dataSourceProvider)
	{
		this.dataSourceProvider = dataSourceProvider;
	}	

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}	

	public void setChartProvider(ChartProvider chartProvider)
	{
		this.chartProvider = chartProvider;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}	

	public int getChartType()
	{
		return chartType;
	}

	public void setChartType(int chartType)
	{
		this.chartType = chartType;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public ChartValue[] getChartValues()
	{
		return chartValues;
	}

	public String getXAxisLabel()
	{
		return xAxisLabel;
	}

	public void setXAxisLabel(String axisLabel)
	{
		xAxisLabel = axisLabel;
	}

	public String getYAxisLabel()
	{
		return yAxisLabel;
	}

	public void setYAxisLabel(String axisLabel)
	{
		yAxisLabel = axisLabel;
	}

	public void setParameterProvider(ParameterProvider parameterProvider)
	{
		this.parameterProvider = parameterProvider;
	}

	public int getOrientation()
	{
		return orientation;
	}

	public void setOrientation(int orientation)
	{
		this.orientation = orientation;
	}

	public boolean isShowLegend()
	{
		return showLegend;
	}

	public void setShowLegend(boolean showLegend)
	{
		this.showLegend = showLegend;
	}

	public boolean isShowTitle()
	{
		return showTitle;
	}

	public void setShowTitle(boolean showTitle)
	{
		this.showTitle = showTitle;
	}

	public int getReportId()
	{
		return reportId;
	}

	public void setReportId(int reportId)
	{
		this.reportId = reportId;
	}

	public void setReportProvider(ReportProvider reportProvider)
	{
		this.reportProvider = reportProvider;
	}

	public boolean isShowValues()
	{
		return showValues;
	}

	public void setShowValues(boolean showValues)
	{
		this.showValues = showValues;
	}

	public void setDirectoryProvider(DirectoryProvider directoryProvider)
	{
		this.directoryProvider = directoryProvider;
	}

	public void setPropertiesProvider(PropertiesProvider propertiesProvider)
	{
		this.propertiesProvider = propertiesProvider;
	}
}