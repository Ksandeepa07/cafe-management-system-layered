package lk.ijse.cafe_au_lait.bo.custom.impl;

import lk.ijse.cafe_au_lait.bo.custom.CustomerBO;
import lk.ijse.cafe_au_lait.dao.custom.CustomerDAO;
import lk.ijse.cafe_au_lait.dao.DAOFactory;
import lk.ijse.cafe_au_lait.dto.CustomerDTO;
import lk.ijse.cafe_au_lait.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO<CustomerDTO,String > {
    CustomerDAO customerDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ArrayList<CustomerDTO> customerData=new ArrayList<>();
    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.save(new Customer(customerDTO.getCustId(),customerDTO.getCustName(),customerDTO.getCustContact(),customerDTO.getCustEmail()));

    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException {
        ArrayList<Customer>load= customerDAO.getAll();
        for (Customer customer : load) {
            customerData.add(new CustomerDTO(customer.getCustId(),customer.getCustName(),customer.getCustContact(),customer.getCustEmail()) );
        }
        return customerData;
    }

    @Override
    public CustomerDTO searchById(String id) throws SQLException {
        Customer customer=customerDAO.searchById(id);
        return new CustomerDTO(customer.getCustId(),customer.getCustName(),customer.getCustContact(),customer.getCustEmail());
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.update(new Customer(customerDTO.getCustId(),customerDTO.getCustName(),customerDTO.getCustContact(),customerDTO.getCustEmail()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<String> loadCustId() {
        return null;
    }

    @Override
    public int countId() throws SQLException {
        return 0;
    }
}
