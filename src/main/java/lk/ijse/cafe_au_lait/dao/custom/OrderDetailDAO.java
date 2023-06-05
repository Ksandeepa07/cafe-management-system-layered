package lk.ijse.cafe_au_lait.dao.custom;

import javafx.scene.chart.PieChart;
import lk.ijse.cafe_au_lait.dao.CrudDAO;
import lk.ijse.cafe_au_lait.entity.OrdeDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrdeDetail,String> {
    ArrayList<PieChart.Data> setData() throws SQLException;
}
