package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import code.Matrix;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestMatrixPublic {
	
	String grid0 = "5,5;2;3,4;1,2;0,3,1,4;2,3;4,4,0,2,0,2,4,4;2,2,91,2,4,62";
	String grid1 = "5,5;1;1,4;1,0;0,4;0,0,2,2;3,4,4,2,4,2,3,4;0,2,32,0,1,38";
	String grid2 = "5,5;2;3,2;0,1;4,1;0,3;1,2,4,2,4,2,1,2,0,4,3,0,3,0,0,4;1,1,77,3,4,34";
	String grid3 = "5,5;1;0,4;4,4;0,3,1,4,2,1,3,0,4,1;4,0;2,4,3,4,3,4,2,4;0,2,98,1,2,98,2,2,98,3,2,98,4,2,98,2,0,1";
	String grid4 = "5,5;1;0,4;4,4;0,3,1,4,2,1,3,0,4,1;4,0;2,4,3,4,3,4,2,4;0,2,98,1,2,98,2,2,98,3,2,98,4,2,98,2,0,98,1,0,98";
	String grid5 = "5,5;2;0,4;3,4;3,1,1,1;2,3;3,0,0,1,0,1,3,0;4,2,54,4,0,85,1,0,43";
	String grid6 = "5,5;2;3,0;4,3;2,1,2,2,3,1,0,0,1,1,4,2,3,3,1,3,0,1;2,4,3,2,3,4,0,4;4,4,4,0,4,0,4,4;1,4,57,2,0,46";
	String grid7 = "5,5;3;1,3;4,0;0,1,3,2,4,3,2,4,0,4;3,4,3,0,4,2;1,4,1,2,1,2,1,4,0,3,1,0,1,0,0,3;4,4,45,3,3,12,0,2,88";
	String grid8 = "5,5;2;4,3;2,1;2,0,0,4,0,3,0,1;3,1,3,2;4,4,3,3,3,3,4,4;4,0,17,1,2,54,0,0,46,4,1,22";
	String grid9 = "5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";
	String grid10 = "5,5;4;1,1;4,1;2,4,0,4,3,2,3,0,4,2,0,1,1,3,2,1;4,0,4,4,1,0;2,0,0,2,0,2,2,0;0,0,62,4,3,45,3,3,39,2,3,40";
	
	
	@Test(timeout = 10000)
	public void testa0() throws Exception {
		String solution = Matrix.solve(grid0, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testa1() throws Exception {
		String solution = Matrix.solve(grid1, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testa2() throws Exception {
		String solution = Matrix.solve(grid2, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	
	@Test(timeout = 10000)
	public void testa3() throws Exception {
		String solution = Matrix.solve(grid3, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}
	
	@Test(timeout = 10000)
	public void testa4() throws Exception {
		String solution = Matrix.solve(grid4, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", solution.equals("No Solution"));
	}
	
	@Test(timeout = 10000)
	public void testa5() throws Exception {
		String solution = Matrix.solve(grid5, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 10000)
	public void testa6() throws Exception {
		String solution = Matrix.solve(grid6, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 30000)
	public void testa7() throws Exception {
		String solution = Matrix.solve(grid7, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	@Test(timeout = 30000)
	public void testa8() throws Exception {
		String solution = Matrix.solve(grid8, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 30000)
	public void testa9() throws Exception {
		String solution = Matrix.solve(grid9, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 60000)
	public void testaz10() throws Exception {
		String solution = Matrix.solve(grid10, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}

	@Test(timeout = 10000)
	public void testb0() throws Exception {
		String solution = Matrix.solve(grid0, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testb1() throws Exception {
		String solution = Matrix.solve(grid1, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testb2() throws Exception {
		String solution = Matrix.solve(grid2, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	
	@Test(timeout = 10000)
	public void testb3() throws Exception {
		String solution = Matrix.solve(grid3, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}
	
	@Test(timeout = 10000)
	public void testb4() throws Exception {
		String solution = Matrix.solve(grid4, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", solution.equals("No Solution"));
	}
	
	@Test(timeout = 10000)
	public void testb5() throws Exception {
		String solution = Matrix.solve(grid5, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 10000)
	public void testb6() throws Exception {
		String solution = Matrix.solve(grid6, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 30000)
	public void testb7() throws Exception {
		String solution = Matrix.solve(grid7, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.",applyPlan(grid7, solution) );
	}
	
	@Test(timeout = 30000)
	public void testb8() throws Exception {
		String solution = Matrix.solve(grid8, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.",applyPlan(grid8, solution));
	}
	
	@Test(timeout = 30000)
	public void testb9() throws Exception {
		String solution = Matrix.solve(grid9, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 60000)
	public void testbz10() throws Exception {
		String solution = Matrix.solve(grid10, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}
	
	@Test(timeout = 10000)
	public void testc0() throws Exception {
		String solution = Matrix.solve(grid0, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testc1() throws Exception {
		String solution = Matrix.solve(grid1, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testc2() throws Exception {
		String solution = Matrix.solve(grid2, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	
	@Test(timeout = 10000)
	public void testc3() throws Exception {
		String solution = Matrix.solve(grid3, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}
	
	
	@Test(timeout = 10000)
	public void testc4() throws Exception {
		String solution = Matrix.solve(grid4, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", solution.equals("No Solution"));
	}
	
	@Test(timeout = 10000)
	public void testc5() throws Exception {
		String solution = Matrix.solve(grid5, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 30000)
	public void testc6() throws Exception {
		String solution = Matrix.solve(grid6, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 400000)
	public void testc7() throws Exception {
		String solution = Matrix.solve(grid7, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.",  applyPlan(grid7, solution));
	}
	
	@Test(timeout = 400000)
	public void testc8() throws Exception {
		String solution = Matrix.solve(grid8, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 400000)
	public void testc9() throws Exception {
		String solution = Matrix.solve(grid9, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	
	@Test(timeout = 10000)
	public void testd0() throws Exception {
		String solution = Matrix.solve(grid0, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testd1() throws Exception {
		String solution = Matrix.solve(grid1, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testd2() throws Exception {
		String solution = Matrix.solve(grid2, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	
	@Test(timeout = 10000)
	public void testd3() throws Exception {
		String solution = Matrix.solve(grid3, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}
	
	@Test(timeout = 10000)
	public void testd5() throws Exception {
		String solution = Matrix.solve(grid5, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 10000)
	public void testd6() throws Exception {
		String solution = Matrix.solve(grid6, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	

	@Test(timeout = 10000)
	public void teste0() throws Exception {
		String solution = Matrix.solve(grid0, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void teste1() throws Exception {
		String solution = Matrix.solve(grid1, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void teste2() throws Exception {
		String solution = Matrix.solve(grid2, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	
	@Test(timeout = 10000)
	public void teste3() throws Exception {
		String solution = Matrix.solve(grid3, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}
	
	@Test(timeout = 10000)
	public void teste4() throws Exception {
		String solution = Matrix.solve(grid4, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", solution.equals("No Solution"));
	}
	
	@Test(timeout = 10000)
	public void teste5() throws Exception {
		String solution = Matrix.solve(grid5, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 30000)
	public void teste6() throws Exception {
		String solution = Matrix.solve(grid6, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 400000)
	public void teste7() throws Exception {
		String solution = Matrix.solve(grid7, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	@Test(timeout = 400000)
	public void teste8() throws Exception {
		String solution = Matrix.solve(grid8, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 400000)
	public void teste9() throws Exception {
		String solution = Matrix.solve(grid9, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 10000)
	public void testf0() throws Exception {
		String solution = Matrix.solve(grid0, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testf1() throws Exception {
		String solution = Matrix.solve(grid1, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testf2() throws Exception {
		String solution = Matrix.solve(grid2, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	
	@Test(timeout = 10000)
	public void testf3() throws Exception {
		String solution = Matrix.solve(grid3, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}
	
	@Test(timeout = 10000)
	public void testf4() throws Exception {
		String solution = Matrix.solve(grid4, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", solution.equals("No Solution"));
	}
	
	@Test(timeout = 10000)
	public void testf5() throws Exception {
		String solution = Matrix.solve(grid5, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 30000)
	public void testf6() throws Exception {
		String solution = Matrix.solve(grid6, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 400000)
	public void testf7() throws Exception {
		String solution = Matrix.solve(grid7, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	@Test(timeout = 400000)
	public void testf8() throws Exception {
		String solution = Matrix.solve(grid8, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 400000)
	public void testf9() throws Exception {
		String solution = Matrix.solve(grid9, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 10000)
	public void testg0() throws Exception {
		String solution = Matrix.solve(grid0, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testg1() throws Exception {
		String solution = Matrix.solve(grid1, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testg2() throws Exception {
		String solution = Matrix.solve(grid2, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	
	@Test(timeout = 10000)
	public void testg3() throws Exception {
		String solution = Matrix.solve(grid3, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}
	
	@Test(timeout = 10000)
	public void testg4() throws Exception {
		String solution = Matrix.solve(grid4, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", solution.equals("No Solution"));
	}
	
	@Test(timeout = 10000)
	public void testg5() throws Exception {
		String solution = Matrix.solve(grid5, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 30000)
	public void testg6() throws Exception {
		String solution = Matrix.solve(grid6, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}

	@Test(timeout = 400000)
	public void testg7() throws Exception {
		String solution = Matrix.solve(grid7, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	@Test(timeout = 400000)
	public void testg8() throws Exception {
		String solution = Matrix.solve(grid8, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 400000)
	public void testg9() throws Exception {
		String solution = Matrix.solve(grid9, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 400000)
	public void testgz10() throws Exception {
		String solution = Matrix.solve(grid10, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}

	
	@Test(timeout = 10000)
	public void testh0() throws Exception {
		String solution = Matrix.solve(grid0, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}
	
	@Test(timeout = 10000)
	public void testh1() throws Exception {
		String solution = Matrix.solve(grid1, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}
	
	@Test(timeout = 10000)
	public void testh2() throws Exception {
		String solution = Matrix.solve(grid2, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}
	
	@Test(timeout = 10000)
	public void testh3() throws Exception {
		String solution = Matrix.solve(grid3, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}
	
	@Test(timeout = 10000)
	public void testh4() throws Exception {
		String solution = Matrix.solve(grid4, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", solution.equals("No Solution"));
	}
	
	@Test(timeout = 10000)
	public void testh5() throws Exception {
		String solution = Matrix.solve(grid5, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 30000)
	public void testh6() throws Exception {
		String solution = Matrix.solve(grid6, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}

	@Test(timeout = 400000)
	public void testh7() throws Exception {
		String solution = Matrix.solve(grid7, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	@Test(timeout = 400000)
	public void testh8() throws Exception {
		String solution = Matrix.solve(grid8, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 400000)
	public void testh9() throws Exception {
		String solution = Matrix.solve(grid9, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 400000)
	public void testhz10() throws Exception {
		String solution = Matrix.solve(grid10, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}

	
	 static class TH {
			int m;
			int n;
			int x10;
			int x11;
			int x00;
			int x01;
			int m1;
			int m2;
			int m3;
			ArrayList<String> xyz;
			ArrayList<String> xyzw;
			ArrayList<String> m4;
			HashMap<String,String> m5;
			HashMap<String,Integer> m7;
			HashMap<String,Integer> m6;
			ArrayList<String> m10;
			int m23;
			boolean ll;

			public TH(int m, int n, int x10, int x11, int x00, int x01, int m1,
					ArrayList<String> xyz,ArrayList<String> m4,
					HashMap<String, String> m5, HashMap<String, Integer> m7) {
				this.m = m;
				this.n = n;
				this.x10 = x10;
				this.x11 = x11;
				this.x00 = x00;
				this.x01 = x01;
				this.m1 = m1;
				this.m2 = 0;
				this.m3 = 0;
				this.xyz = xyz;
				this.xyzw = new ArrayList<String>();
				this.m4 = m4;
				this.m5 = m5;
				this.m7 = m7;
				this.m6 = new HashMap<String,Integer>();
				this.m10 = new ArrayList<String>();
				this.m23 = 0;
				this.ll = false;
			}

			public String f1(int x, int y) {
				return x+","+y;
			}
			
			public boolean f12(int x, int y) {
				return x <0 || x>m || y<0 || y>n ? false: true;
			}
			
			public ArrayList<String> f52(int x, int y) {
				ArrayList<String> result = new ArrayList<String>();
				if(f12(x-1,y))
					result.add((x-1)+","+y);
				if(f12(x+1,y))
					result.add((x+1)+","+y);
				if(f12(x,y-1))
					result.add(x+","+(y-1));
				if(f12(x,y+1))
					result.add(x+","+(y+1));
				return result;
			}

			public boolean f42(int x, int y) {
				String ln = f1(x,y);
				if(this.xyz.contains(ln) || this.xyzw.contains(ln)
						|| (this.m7.containsKey(ln) && this.m7.get(ln)>97))
					return false;
				return true;	
			}
			
			public boolean f2() { 
				if(!f42(x00-1, x01)) return false;
				if(x00 - 1 >= 0)
					x00--;
				ll2();
				return true;
			}
			
			public boolean f3() {
				if(!f42(x00+1, x01)) return false;
				if(x00 + 1 < this.m)
					x00++;
				ll2();
				return true;
					
			}
			
			public boolean applyLeft() {
				if(!f42(x00, x01-1)) return false;
				if(x01 - 1 >= 0)
					x01--;
				ll2();
				return true;

			}
			
			public boolean f4() {
				if(!f42(x00, x01+1)) return false;
				if(x01 + 1 < this.n)
					x01++;
				ll2();
				return true;
			}
			
			public boolean f100() {
				ArrayList<String> acdc = f52(x00,x01);
				if(acdc.size()>0) {
					for(String led:acdc) {
						if(xyz.contains(led)) {
							xyz.remove(led);
							this.m23++;
						}
						if(xyzw.contains(led)) {
							xyzw.remove(led);
							this.m23++;
						}
					}
					m3 +=20;
					if(m3 == 100)
						ll = true;
					
					ll2();
					return true;
				}
				return false;
				
			}
			
			public boolean f209() {
				String floyd = f1(x00,x01);
				if(m2 <= m1) {
					if(m7.containsKey(floyd)) {
						m2++;
						m6.put(floyd, m7.get(floyd));
						m7.remove(floyd);
						ll2();
						return true;
					}
				}
				
				return false;
			}
			
			public boolean f220() {
				if(x00 == x10 && x01 == x11 && m2 >0) {
					m2 = 0;
					m6.clear();
					ll2();
					return true;
				}
				return false;
			}
			
			public boolean f320() {
				String floyd = f1(x00,x01);
				if(this.m5.containsKey(floyd)) {
					String tofloyd = this.m5.get(floyd);
					String [] s = tofloyd.split(",");
					x00 = Integer.parseInt(s[0]);
					x01 = Integer.parseInt(s[1]);
					ll2();
					return true;
				}
				return false;
				
			}
			
			public boolean f32() {
				if(this.m4.contains(f1(x00,x01))) {
					m3 = (m3 -20 <0) ? 0 : m3 -20;
					for(String abc: m7.keySet()) {
						int beatles = m7.get(abc)-20 <0? 0 : m7.get(abc)-20;
						m7.put(abc,beatles);
					}
					for(String abc: m6.keySet()) {
						if(m6.get(abc)<100) {
							int beatles = m6.get(abc)-20 <0? 0 : m6.get(abc)-20;
							m6.put(abc,beatles);
						}
					}
					this.m4.remove(f1(x00,x01));
					return true;
				}
				return false;
			}
			
			public void ll2() {
				
				HashMap<String,Integer> newm7 = new HashMap<String,Integer>();
				
				for(String abc: m6.keySet()) {
					int beatles = m6.get(abc)+2;
					if(beatles >= 100)  {
						this.edu(abc);
						m6.put(abc,100);
					}
					else 
						m6.put(abc,beatles);
				  }
				
				for(String abc: m7.keySet()) {
					int beatles = m7.get(abc)+2;
					if(beatles >= 100) {
						this.edu(abc);
						xyzw.add(abc);
					}
					else 
						newm7.put(abc,beatles);
				}
				this.m7 = newm7;
			}
			
			public void edu(String abc) {
				if(!this.m10.contains(abc))
					this.m10.add(abc);
			}
			
			public boolean grace() {	
				return !this.ll && this.m3<100 && this.m7.size() == 0 
						&& this.xyzw.size() == 0 && this.x00 == this.x10
						&& this.x01 == this.x11;
			}
	 }

			
			public static boolean applyPlan(String grid, String solution) {
				String[] solutionArray  = solution.split(";");
				String plan = solutionArray[0];
				int blue = Integer.parseInt(solutionArray[1]);
				int doors = Integer.parseInt(solutionArray[2]);
				
				plan.replace(" ", "");
				plan.replace("\n", "");
				plan.replace("\r", "");
				plan.replace("\n\r", "");
				plan.replace("\t", "");

				String[] actions = plan.split(",");
				
				String[] gridArray=  grid.split(";");
				String[] dimensions = gridArray[0].split(",");
				int m = Integer.parseInt(dimensions[0]);
				int n = Integer.parseInt(dimensions[1]);
				
				int capacity = Integer.parseInt(gridArray[1]);
				
				String[] neo = gridArray[2].split(",");
				int x00 = Integer.parseInt(neo[0]);
				int x01 = Integer.parseInt(neo[1]);
				
				String[] booth = gridArray[3].split(",");
				int x10 = Integer.parseInt(booth[0]);
				int x11 = Integer.parseInt(booth[1]);
				
				String[] ag = gridArray[4].split(",");
				ArrayList<String> xyz = new ArrayList<String>();
				for(int i = 0;i< ag.length -1; i+=2) {
					xyz.add(ag[i]+","+ag[i+1]);
				}
				
				String[] pl = gridArray[5].split(",");
				ArrayList<String> m4 = new ArrayList<String>();
				for(int i = 0;i< pl.length -1; i+=2) {
					m4.add(pl[i]+","+pl[i+1]);
				}

				String[] pas = gridArray[6].split(",");
				HashMap<String,String> m5 = new HashMap<String,String>();
				for(int i = 0;i< pas.length -3; i+=4) {
					m5.put(pas[i]+","+pas[i+1],pas[i+2]+","+pas[i+3]);
				}
				
				String[] hstg = gridArray[7].split(",");
				HashMap<String,Integer> m7 = new HashMap<String,Integer>();
				for(int i = 0;i< hstg.length -2; i+=3) {
					m7.put(hstg[i]+","+hstg[i+1],Integer.parseInt(hstg[i+2]));
				}
				
				TH s = new TH(m,n,x10,x11,x00, x01,capacity, xyz,m4,m5,m7);
				boolean linkin = true;
				
				for (int i = 0; i < actions.length; i++) {
				
					switch (actions[i]) {
					case "up":
						linkin = s.f2();
						break;
					case "down":
						linkin = s.f3();
						break;
					case "right":
						linkin = s.f4();
						break;
					case "left":
						linkin = s.applyLeft();
						break;
					case "carry":
						linkin = s.f209();
						break;
					case "drop":
						linkin = s.f220();
						break;
					case "fly":
						linkin = s.f320();
						break;
					case "takePill":
						linkin = s.f32();
						break;
					case "kill":
						linkin = s.f100();
						break;
					default: linkin = false; break;
								
					}

					if(!linkin)
						return false;		
					
						
						
				}
				return s.grace() && s.m23 == doors && s.m10.size() == blue;
			}
}
