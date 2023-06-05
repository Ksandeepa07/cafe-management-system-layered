package lk.ijse.cafe_au_lait.bo.custom;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import lk.ijse.cafe_au_lait.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface HomeBO<T,ID> extends SuperBO {
    int countCustomerId() throws SQLException;

    int countOrdersId() throws SQLException;

    int countTodayIncome() throws SQLException;

    ArrayList<PieChart.Data> getPieChartData() throws SQLException;

    XYChart.Series getLineChartData() throws SQLException;

    List<Image> getEventImage() throws SQLException;
}
