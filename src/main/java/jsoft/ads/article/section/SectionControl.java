package jsoft.ads.article.section;

import jsoft.*;
import jsoft.objects.*;

import java.util.*;

public class SectionControl {
	
	private SectionModel sm;
	
	public SectionControl(ConnectionPool cp) {
		this.sm = new SectionModel(cp);
	}
	
	protected void finalize() throws Throwable{
		this.sm = null;
	}
	
	public ConnectionPool getCP(){
		return this.sm.getCP();
	}
	
	public void releaseConnection() {
		this.sm.releaseConnection();
	}
	
	
	//-----------------------
	
	public boolean addSection(SectionObject item) {
		return this.sm.addSection(item);
	}
	
	public boolean editSection(SectionObject item) {
		return this.sm.editSection(item);
	}
	
	public boolean delSection(SectionObject item) {
		return this.sm.delSection(item);
	}
	
	//-------------------------
	

	public SectionObject getSectionObject(Short id) {
		return this.sm.getSectionObject(id);
	}
	
	//-----------------------
	
	public String viewSections(SectionObject similar, SectionSort ss, short page, byte total ) {
		//Lấy dữ liệu
		
		ArrayList<SectionObject> items = this.sm.getSectionObjects(similar, ss, page, total);
		
		return SectionLibrary.viewSections(items);
	}
	
	public String getSectionNames(SectionObject similar, SectionSort ss, short page, byte total ) {
		ArrayList<SectionObject> items = this.sm.getSectionObjects(similar, ss, page, total);
		
		return SectionLibrary.getSectionNames(items);
	}
	public static void main(String[] args) {
		//tạo đối tượng thực thi CN mức control
		SectionControl sc = new SectionControl(null);
		
		//lấy cấu trúc trình bày
		String view = sc.getSectionNames(null, SectionSort.ID, (short)0, (byte)0);
		
		//trả về kết nối
		sc.releaseConnection();
		
		//hiển thị
		System.out.println(view);
	}
}
