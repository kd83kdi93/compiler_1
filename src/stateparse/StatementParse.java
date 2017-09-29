package stateparse;

import parse.ParseBase;
import returnvalue.ReturnValue;

public class StatementParse extends ParseBase {

	private SentenceParse sentenceParse = new SentenceParse();
	
	@Override
	protected ReturnValue parse() {
		return statement();
	}

	private ReturnValue statement() {
		ReturnValue head = sentenceParse.getParseTree();
		ReturnValue oldHead = head;
		ReturnValue next = null;
		while (head != null) {
			next = sentenceParse.getParseTree();
			head.setNextSentence(next);
			head = next;
		}
		return oldHead;
	}

}
