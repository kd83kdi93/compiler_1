package stateparse;

import parse.ParseBase;
import parseinterface.ParseInterface;
import parsemanager.ParseManager;
import returnvalue.ReturnValue;

public class StatementParse extends ParseBase {

	@Override
	protected ReturnValue parse() {
		return statement();
	}

	private ReturnValue statement() {
		ParseInterface sentenceParse = ParseManager.getParse(SentenceParse.class);
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
