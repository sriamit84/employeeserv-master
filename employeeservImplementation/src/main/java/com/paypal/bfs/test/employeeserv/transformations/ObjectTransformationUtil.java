package com.paypal.bfs.test.employeeserv.transformations;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.jpa.model.AddressEntity;
import com.paypal.bfs.test.employeeserv.jpa.model.EmployeesEntity;
import com.paypal.bfs.test.employeeserv.validators.ValidationUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;


public final class ObjectTransformationUtil {

    static{
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class aClass, Object o) {
                return ValidationUtil.PARSE_DATE_STRING.apply((String)o);
            }
        }, Date.class);
    }

    private ObjectTransformationUtil() {
    }

    public static Employee transformDAOToEmployee(EmployeesEntity employeesEntity) throws InvocationTargetException, IllegalAccessException {

        Employee employee = new Employee();
        BeanUtils.copyProperties(employee,employeesEntity);
        employee.setDateOfBirth(ValidationUtil.CONVERT_DATE_STRING.apply(employeesEntity.getDateOfBirth()));
        employee.setAddress(transformDAOToAddress(employeesEntity.getAddressEntity()));
        return employee;
    }

    public static Address transformDAOToAddress(AddressEntity addressEntity) throws InvocationTargetException, IllegalAccessException {
        Address address = new Address();
        BeanUtils.copyProperties(address,addressEntity);
        return address;
    }


    public static EmployeesEntity transformModelToEmployeeEntity(Employee employee) throws InvocationTargetException, IllegalAccessException {
        EmployeesEntity employeesEntity = new EmployeesEntity();
        BeanUtils.copyProperties(employeesEntity,employee);
        employeesEntity.setAddressEntity(transformModelToAddressEntity(employee.getAddress()));
        return employeesEntity;
    }
    public static AddressEntity transformModelToAddressEntity(Address address) throws InvocationTargetException, IllegalAccessException {
        AddressEntity addressEntity = new AddressEntity();
        BeanUtils.copyProperties(addressEntity,address);
        return addressEntity;
    }
}
