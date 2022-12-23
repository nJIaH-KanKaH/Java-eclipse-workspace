import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ListCellRendererCustomable implements ListCellRenderer<Tour> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Tour> arg0, Tour arg1, int arg2, boolean arg3,
			boolean arg4) {
		JLabel label=new JLabel(arg1.getCountryName(),arg1.getCountryIcon(),JLabel.LEFT);
		if(arg3) {
			label.setBackground(Color.ORANGE);
			label.setOpaque(true);//helps to show labels background
		}
		return label;
	}

}
