package owlapi.tutorial.msc;

public class Clothes {
	private  String IDcategory;
	private  String category;
	private  String title;
	private  String OWLClass;
	public Clothes(String category, String title, String OWLClass, String IDcategory){
        this.category = category;
        this.title = title;
        this.OWLClass = OWLClass;
        this.IDcategory = IDcategory;
	}
	public String getTitle(){
		return title;
	}
	public String getCategory(){
		return category;
	}
	public String getOWLClass() {
		return OWLClass;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setOWLClass(String OWLClass) {
		this.OWLClass = OWLClass;
	}
	public String getIDcategory() {
		return IDcategory;
	}
	public void setIDcategory(String IDcategory) {
		this.IDcategory = IDcategory;
	}
}