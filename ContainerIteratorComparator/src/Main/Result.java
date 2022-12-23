package Main;

public class Result {

    private long id;
    private String lastName;
    private int course;
    private int group;
    private int counter=0;

    public Result(long id, String lastName, int course, int group) {
        this.id = id;
        this.lastName = lastName;
        this.course = course;
        this.group = group;
        this.counter++;
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCourse() {
        return course;
    }

    public int getGroup() {
        return group;
    }
    
    public int getCounter() {
    	return counter;
    }
    
    public void inc() {
    	counter++;
    }

    public String toXML() {
        return String.format("<Result id='%s' lastName='%s' course='%s' group='%s'/>", id, lastName, course, group);
    }

	@Override
	public String toString() {
		return "id=" + id + " lastName=" + lastName + " course=" + course + " group=" + group;
	}
	
	public boolean equals(Result r) {
//		return this.lastName.equals(r.lastName);
		return this.course==r.course &&
				this.group==r.group &&
				this.lastName.equals(r.lastName) &&
				this.id==r.id;
	}
}