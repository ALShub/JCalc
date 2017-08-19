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
	
	private void doDisplayCurr() {
		if (curr.equals("")) txtDisplay.setText("0"); else txtDisplay.setText(curr);
	}
	private void doDisplayExpr() {
		String s = "";
		if (expr.equals("0")) s=""; else s=expr;
		s = s.replace('*', 'x').replace('/', '÷').replace('.', ',');		
		txtExpr.setText(s);
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
    	if (id.equals("btn9")) curr += "9";
    	else
    		if (id.equals("btn8")) curr += "8";
        	else
        		if (id.equals("btn7")) curr += "7";
            	else
            		if (id.equals("btn6")) curr += "6";
                	else
                		if (id.equals("btn5")) curr += "5";
                    	else
                    		if (id.equals("btn4")) curr += "4";
                        	else
                        		if (id.equals("btn3")) curr += "3";
                            	else
                            		if (id.equals("btn2")) curr += "2";
                                	else
                                		if (id.equals("btn1")) curr += "1";
                                    	else
                                    		if (id.equals("btn0")) curr += "0";   
                                    		else
                                    			if (id.equals("btnVirg")) {
                                    				if ((!curr.contains("."))&&(!curr.isEmpty())) curr += ".";
                                    			}
    	
    	doDisplayCurr();    		
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
     	String id = ((Node) event.getSource()).getId();
     	if (id.equals("btnPiu")) curr += "+";
     	else
     		if (id.equals("btnMeno")) curr += "-";
         	else
         		if (id.equals("btnMult")) curr += "*";
             	else
             		if (id.equals("btnDivi")) curr += "/";
        expr += curr;
        curr = "";
        doDisplayExpr();     		
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
    	if (expr.length()>0) {
	    	ScriptEngineManager manager = new ScriptEngineManager();
	    	ScriptEngine engine = manager.getEngineByName("js");
	    	try {    		
	    		expr += curr;
				Object result = engine.eval(expr);
				curr = result.toString();
				if ((curr.contains("."))&&(curr.substring(curr.length()-2, curr.length()).equals(".0"))) {
					String s = curr.substring(0,curr.length()-2);					
					curr = s;
				}
				expr = "";
				doDisplayCurr();
				doDisplayExpr();     	    	
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void doDelDigit(ActionEvent event) {
      if (!curr.isEmpty()) {
    	  curr = curr.substring(0,curr.length()-1);
    	  if (curr.equals("-")) curr="";
      }
	  doDisplayCurr();      
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
			expr += curr;
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
}
