package EPDLEditor.UI;

import java.util.ArrayList;

import EPDLEditor.Types.Identifiable;

public class PopulateListsUtils {
	public static int[] getIDs4Strings(String[] selected, ArrayList<?> list) {
		if (selected==null) return new int[0];
		int[] rslt = new int[selected.length];
		for (int i=0; i<rslt.length;i++){
			int k=0;
			for (Identifiable val:(ArrayList<Identifiable>)list){
				if (val.getID().compareTo(selected[i])==0){
					rslt[i] = k;
					break;
				}	
				k++;
			}
		}
		return rslt;
	}
	public static String[] getStrings4IDs(int[] selected, ArrayList<?> list) {
		String[] rslt = new String[selected.length];
		for (int i=0; i<selected.length;i++){
			rslt[i] = new String(((Identifiable)list.get(selected[i])).getID());
		}
		return rslt;
	}
}
