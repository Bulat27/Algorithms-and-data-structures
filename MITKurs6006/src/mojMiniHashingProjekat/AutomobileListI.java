package mojMiniHashingProjekat;

public interface AutomobileListI {
	
	public void insertFirst(Automobile x)throws Exception;
	public void delete(AutomobileNode x)throws Exception;
	public AutomobileNode search(String idNumber) throws Exception;
}
