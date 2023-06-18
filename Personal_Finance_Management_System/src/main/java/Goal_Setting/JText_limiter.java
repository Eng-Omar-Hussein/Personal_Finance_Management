/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Goal_Setting;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
/**
 *
 * @author Mohamed Ahmed
 */
public class JText_limiter extends PlainDocument{
    private final int limit;
    
    public JText_limiter(int limit) {
        this.limit = limit;
    }
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (getLength() + str.length() <= limit) {
            super.insertString(offs, str, a);
        }
    }
}
