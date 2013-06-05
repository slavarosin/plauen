package net.gobro.plauen.service;

import net.gobro.plauen.model.Chart;
import net.gobro.plauen.service.ChartService;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;


@DataSet
public class ChartServiceTest extends BaseDatabaseTest {
	@SpringBeanByType
	private ChartService chartService;

	@Test
	public void testFetchChart() {
		Chart chart = chartService.fetchChart(1);
		Assert.assertNotNull(chart);
		Assert.assertEquals("user2", chart.getRows().get(0).getUsername());
		Assert.assertEquals(new Integer(100), chart.getRows().get(0)
				.getPoints());
		Assert.assertEquals("user1", chart.getRows().get(1).getUsername());
		Assert
				.assertEquals(new Integer(37), chart.getRows().get(1)
						.getPoints());
	}

	@Test
	public void testFetchChartNoPlayers() {
		Chart chart = chartService.fetchChart(2);
		Assert.assertNotNull(chart);
		Assert.assertNotNull(chart.getRows());
		Assert.assertTrue(chart.getRows().isEmpty());
	}

	@Test
	public void testFetchChartNoBids() {
		Chart chart = chartService.fetchChart(3);
		Assert.assertNotNull(chart);
		Assert.assertNotNull(chart.getRows());
		Assert.assertEquals(3, chart.getRows().size());
	}
}
