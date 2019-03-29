package app.examples.DragAndDrop.DragFrom3TablesTo1Table.model;

public class TabelleRegistraturplan {
	
		private String sign;
		private String text;
		private int total;
		private int teil;
		private int zaehler;
							
		public TabelleRegistraturplan(String sign, String text, int total, int teil, int zaehler) {
			this.sign = sign;
			this.text = text;
			this.total = total;
			this.teil = teil;
			this.zaehler = zaehler;
			
		}
		
		// Getter
		public String getSign(){ return sign;	}
		public String getText(){ return text;	}
		public int getTotal(){ return total;	}
		public int getTeil(){ return teil;	}
		public int getZaehler(){ return zaehler;	}
		
		// Setter
		public void setSign(String sign){ this.sign = sign; }
		public void setText(String text){ this.text = text; }
		public void setTotal(int total){ this.total = total; }
		public void setTeil(int teil){ this.teil = teil; }
		public void setZaehler(int zaehler){ this.zaehler = zaehler; }

		@Override
		public String toString() {
			return "TabelleRegistraturplan [sign=" + sign + ", text=" + text + ", total=" + total + ", teil=" + teil
					+ ", zaehler=" + zaehler + "]";
		}
	
}
