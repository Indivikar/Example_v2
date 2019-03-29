package app.examples.DragAndDrop.DragFrom3TablesTo1Table.model;

public class TabelleHaupttitel {
	
		private String sort;
		private String text;
		private int zaehler;
		
				
		public TabelleHaupttitel(String sort, String text, int zaehler) {
			this.sort = sort;
			this.text = text;
			this.zaehler = zaehler;
		}
		
		// Getter
		public String getSort(){ return sort;	}
		public String getText(){ return text;	}
		public int getZaehler(){ return zaehler;	}
		
		// Setter
		public void setSort(String sort){ this.sort = sort; }
		public void setText(String text){ this.text = text; }
		public void setZaehler(int zaehler){ this.zaehler = zaehler; }

		@Override
		public String toString() {
			return "TabelleHaupttitel [sort=" + sort + ", text=" + text + ", zaehler=" + zaehler + "]";
		}
		
}
