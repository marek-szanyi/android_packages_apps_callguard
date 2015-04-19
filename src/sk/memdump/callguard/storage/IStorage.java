package sk.memdump.callguard.storage;

public interface IStorage<Tobj> 
{
	long store(Tobj toStore);
	
	boolean delete(long key);
	
	void request();
	
	void request(long[] keys);
}
