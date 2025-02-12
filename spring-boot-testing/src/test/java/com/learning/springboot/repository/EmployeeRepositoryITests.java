package com.learning.springboot.repository;

import com.learning.springboot.integration.AbstractionContainerBaseTest;
import com.learning.springboot.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryITests extends AbstractionContainerBaseTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup(){
         employeeRepository.deleteAll();
         employee = Employee.builder()
                .firstName("Ram")
                .lastName("Doe")
                .email("ram@gmail.com")
                .build();
    }

    //JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        //given - precondition or setup

        //when - action or behavior that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    //JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeeList_whenFindAll_thenReturnEmployeesList() {
        //given - precondition or setup

        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        //when - action or behavior that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        //then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    //JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        //given - precondition or setup
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        //then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    //JUnit test for get employee by email operation
    @DisplayName("JUnit test get for employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        //given - precondition or setup
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test
        Employee employeeDB = employeeRepository.findByEmail("ram@gmail.com").get();

        //then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    //JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        //given - precondition or setup
        Employee savedEmployee = employeeRepository.save(employee);

        //when - action or behavior that we are going to test
        savedEmployee.setEmail("Ram12@gmail.com");
        savedEmployee.setFirstName("Ramesh");
        employeeRepository.save(savedEmployee);
        Optional<Employee> updatedEmployee = employeeRepository.findById(employee.getId());

        //then - verify the output
        assertThat(updatedEmployee).isPresent();
        assertThat(updatedEmployee.get().getFirstName()).isEqualTo("Ramesh");
        assertThat(updatedEmployee.get().getEmail()).isEqualTo("Ram12@gmail.com");
    }

    //JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        //given - precondition or setup
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        //then - verify the output
        assertThat(employeeOptional).isEmpty();
    }

    // JUnit test for custom query using JPQL with index
    @DisplayName("JUnit test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {
        //given - precondition or setup
        employeeRepository.save(employee);
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();

        //when - action or behavior that we are going to test
        Optional<Employee> savedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        //then - verify the output
        assertThat(savedEmployee).isPresent();
        assertThat(savedEmployee.get()).isNotNull();
    }

    // JUnit test for custom query using JPQL with Named params
    @DisplayName("JUnit test for custom query using JPQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {
        //given - precondition or setup
        employeeRepository.save(employee);
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();

        //when - action or behavior that we are going to test
        Optional<Employee> savedEmployee = employeeRepository.findByJPQLNamedParams( firstName, lastName);

        //then - verify the output
        assertThat(savedEmployee).isPresent();
        assertThat(savedEmployee.get()).isNotNull();
    }

    // JUnit test for custom query using native SQL with index
    @DisplayName("JUnit test for custom query using native SQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){
        //given - precondition or setup
        employeeRepository.save(employee);
//        String firstName = "Ram";
//        String lastName = "Doe";

        //when - action or behavior that we are going to test
        Optional<Employee> savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        //then - verify the output
        assertThat(savedEmployee).isPresent();
        assertThat(savedEmployee.get()).isNotNull();
    }

    // JUnit test for custom query using native SQL with named params
    @DisplayName("JUnit test for custom query using native SQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){
        //given - precondition or setup
        employeeRepository.save(employee);
//        String firstName = "Ram";
//        String lastName = "Doe";

        //when - action or behavior that we are going to test
        Optional<Employee> savedEmployee = employeeRepository.findByNativeSQLNamedParams(employee.getFirstName(), employee.getLastName());

        //then - verify the output
        assertThat(savedEmployee).isPresent();
        assertThat(savedEmployee.get()).isNotNull();
    }
}