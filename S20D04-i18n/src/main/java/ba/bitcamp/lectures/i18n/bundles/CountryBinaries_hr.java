package ba.bitcamp.lectures.i18n.bundles;

import java.util.ListResourceBundle;

public class CountryBinaries_hr extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{ "flag", new Object() {
						@Override
						public String toString() {
							return "Hrvatska zastava";
						}
					} 
				} 
			};
	}

}
