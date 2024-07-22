package Management;

import java.util.Arrays;
import java.util.List;

public class EmpService {
public List<Emp> getEmp(){
	return Arrays.asList(new Emp(100, "Aaron", "gopi"),
			new Emp(102, "Ram", "Finch"),
			new Emp(103, "heath", "leadger"));
			}
}
