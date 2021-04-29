CREATE OR REPLACE PROCEDURE total_emp_by_dept_id (
       
	   v_departmentid IN EMPLOYEES.DEPARTMENT_ID%TYPE,
	   v_firstname    OUT EMPLOYEES.FIRST_NAME%TYPE,
	   v_lastname     OUT  EMPLOYEES.LAST_NAME%TYPE,
	   v_total        OUT  NUMBER )

AS
BEGIN
      SELECT COUNT(employee_id) 
      INTO v_total
      FROM EMPLOYEES
      WHERE DEPARTMENT_ID = v_departmentid;

  SELECT FIRST_NAME, LAST_NAME
  INTO   v_firstname,  v_lastname
  FROM EMPLOYEES
  WHERE DEPARTMENT_ID = v_departmentid
  FETCH FIRST 1 ROWS ONLY;
END;