package edu.neu.coe.info6205.util;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Harshit Raj
 *
 * @param <T>
 */
public interface FileHandler<T extends FileData> {
	public boolean writecsv(String colName,String fileName,List<T> data);

}
