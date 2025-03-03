package com.gipl.escposprint.textparser;

import com.gipl.escposprint.EscPosPrinterCommands;
import com.gipl.escposprint.exceptions.EscPosConnectionException;
import com.gipl.escposprint.exceptions.EscPosEncodingException;

public interface IPrinterTextParserElement {
    int length() throws EscPosEncodingException;
    int noOfCharacter();
    IPrinterTextParserElement print(EscPosPrinterCommands printerSocket) throws EscPosEncodingException, EscPosConnectionException;
}
