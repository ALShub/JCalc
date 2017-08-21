package org.alsoftware.calc;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class calcController {
	private String curr = "";
	private String expr = "";
	private boolean justCommitted = true;
	
	private void doDisplayCurr() {
		if ((curr.contains("."))&&(curr.substring(curr.length()-2, curr.length()).equals(".0"))) {
			String s = curr.substring(0,curr.length()-2);					
			curr = s;
		}
		
		String s = curr.replace('.', ',');		
		if (s.equals("")) txtDisplay.setText("0"); else txtDisplay.setText(s);
	}
	private void doDisplayExpr() {
		String s = "";
		if (expr.equals("0")) s=""; else s=expr;
		s = s.replace('*', 'x').replace('/', '÷').replace('.', ',');		
		txtExpr.setText(s);
	}
	public void test(){
		System.out.print("pippo\n");
	}
		
	@FXML
	private Label txtExpr;	

    @FXML
    private TextField txtDisplay;

    @FXML
    private Button btnPerc;

    @FXML
    private Button btnSqrt;

    @FXML
    private Button btnPotenza;

    @FXML
    private Button btnFraz;

    @FXML
    private Button btnClear1;

    @FXML
    private Button btnClear2;

    @FXML
    private Button btnCanc;

    @FXML
    private Button btnDivi;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button btn9;

    @FXML
    private Button btnMult;

    @FXML
    private Button btn4;
    
    @FXML
    private Button btn3;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btnMeno;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btnPiu;

    @FXML
    private Button btnSegno;

    @FXML
    private Button btn0;

    @FXML
    private Button btnVirg;

    @FXML
    private Button btnUguale;

    @FXML
    void doAddDigit(ActionEvent event) {
    	String id = ((Node) event.getSource()).getId();
    	String digit = "";
    	if (id.equals("btn9")) digit += "9";
    	else
    		if (id.equals("btn8")) digit += "8";
        	else
        		if (id.equals("btn7")) digit += "7";
            	else
            		if (id.equals("btn6")) digit += "6";
                	else
                		if (id.equals("btn5")) digit += "5";
                    	else
                    		if (id.equals("btn4")) digit += "4";
                        	else
                        		if (id.equals("btn3")) digit += "3";
                            	else
                            		if (id.equals("btn2")) digit += "2";
                                	else
                                		if (id.equals("btn1")) digit += "1";
                                    	else
                                    		if (id.equals("btn0")) {
                                    		  if (curr.length()>0)
                                    		  digit += "0";   
                                    		}
                                    		else
                                    			if (id.equals("btnVirg")) {
                                    				if (!curr.contains(".")) digit += '.';                                    				
                                    			}
    	if (!digit.equals("")) performDigit(digit);    	    		
    }
        
    @FXML
    void doClearCurr(ActionEvent event) {
    	curr = "";
    	doDisplayCurr();
    }

    @FXML
    void doClearExpr(ActionEvent event) {
    	curr = "";
    	expr = "";
    	doDisplayCurr();
    	doDisplayExpr();
    }

    @FXML
    void doOperazione(ActionEvent event) {
    	String oper = "";
     	String id = ((Node) event.getSource()).getId();
     	if (id.equals("btnPiu")) oper += "+";
     	else
     		if (id.equals("btnMeno")) oper += "-";
         	else
         		if (id.equals("btnMult")) oper += "*";
             	else
             		if (id.equals("btnDivi")) oper += "/";
      performOperazione(oper);     		
    }
    
    @FXML
    void doInverti(ActionEvent event) {
    	if (curr.startsWith("-")) curr=curr.replace("-", ""); else {
    		if (!curr.isEmpty()) {
    		  String s = "-"+curr;
    		  curr = s;
    		}
    	}
    	doDisplayCurr();    	
    }

    @FXML
    void doTotale(ActionEvent event) {
    	performTotale();
    }

    @FXML
    void doDelDigit(ActionEvent event) {
    	performDelDigit();
    }

    @FXML
    void doPerc(ActionEvent event) {
      if ((!curr.isEmpty())&&(!expr.isEmpty())) {
    	String a = expr.substring(0,expr.length()-1);
    	ScriptEngineManager manager = new ScriptEngineManager();
    	ScriptEngine engine = manager.getEngineByName("js");
    	try {    		
			Object result = engine.eval(a);
			a = result.toString();
			result = engine.eval(a+"*"+curr+"/100");
			curr = result.toString();
			//expr += curr;
			doDisplayCurr();
			doDisplayExpr();     	    				
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
      } else {
    	expr = "";
    	curr = "";
      }
	  doDisplayCurr();
	  doDisplayExpr();
    }

    @FXML
    void doSqrt(ActionEvent event) {    	
    	ScriptEngineManager manager = new ScriptEngineManager();
    	ScriptEngine engine = manager.getEngineByName("js");
    	try {     		
			Object result = engine.eval("Math.sqrt("+curr+")");
			curr = result.toString();
			doDisplayCurr();
			doDisplayExpr();     	    	
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}           
    }

    @FXML
    void doPotenza(ActionEvent event) {
    	performPotenza();
    }

    @FXML
    void doFraz(ActionEvent event) {
    	ScriptEngineManager manager = new ScriptEngineManager();
    	ScriptEngine engine = manager.getEngineByName("js");
    	try {     		
			Object result = engine.eval("1/"+curr);
			curr = result.toString();
			doDisplayCurr();
			doDisplayExpr();     	    	
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}           
    }
    
    public void performTotale() {
    	if ((expr.length()>0)&&(curr.length()>0)) {   
    		System.out.format("curr %s expr %s\n",curr, expr);
    		// correzione segni
    		String segnoExpr = expr.substring(expr.length()-1, expr.length());
    		String segnoCurr = curr.substring(0, 1);
    		if (segnoCurr.equals("-") && segnoExpr.equals("-")) {
                  String s = curr.substring(1,curr.length());      			
    			  curr = s;
    			  s = expr.substring(0,expr.length()-1)+"+";
    			  expr = s;
    		}
    		System.out.format("curr %s expr %s\n",curr, expr);
	    	ScriptEngineManager manager = new ScriptEngineManager();
	    	ScriptEngine engine = manager.getEngineByName("js");
	    	try {    		
	    		expr += curr;
				Object result = engine.eval(expr);
				curr = result.toString();
				expr = "";
				doDisplayCurr();
				doDisplayExpr();
				justCommitted = true;
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    public void performPotenza() {
    	ScriptEngineManager manager = new ScriptEngineManager();
    	ScriptEngine engine = manager.getEngineByName("js");
    	try {     		
			Object result = engine.eval(curr+"*"+curr);
			curr = result.toString();
			doDisplayCurr();
			doDisplayExpr();     	    	
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void performDigit(String digit) {
    	if (justCommitted==true) {
    		  curr="";
    		  justCommitted = false;
    	}
    	
    	if (digit.equals(".")) {
			if ((!curr.contains("."))&&(!curr.isEmpty())) curr += ".";
    	} else curr += digit;
    	doDisplayCurr();
    }
    
    public void performOperazione(String oper) {   
    	if (!curr.equals("")) {
    	  expr += curr;
          expr += oper;
          curr = "";
          doDisplayExpr();
    	}
    }
    
    public void performDelDigit() {
        if (!curr.isEmpty()) {
      	  curr = curr.substring(0,curr.length()-1);
      	  if (curr.equals("-")) curr="";
        }
  	  doDisplayCurr();      
    }
    
}
