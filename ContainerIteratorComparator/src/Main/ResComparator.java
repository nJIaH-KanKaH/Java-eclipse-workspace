package Main;

import java.util.Comparator;

public class ResComparator implements Comparator<Result> {
    @Override
    public int compare(Result lhs, Result rhs) {
        return lhs.getCourse()-rhs.getCourse()!=0 ? 
        		lhs.getCourse()-rhs.getCourse() : lhs.getGroup()-rhs.getGroup()!=0 ?
        		lhs.getGroup()-rhs.getGroup() : lhs.getLastName().compareTo(rhs.getLastName());
    }
}
