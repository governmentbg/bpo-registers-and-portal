package bg.duosoft.bpo.registers.mapper.ipobject.history.agent;

import bg.duosoft.bpo.registers.nonentity.history.agent.HistoryType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

public class AgentHistoryMapperJaxbHelper {

	private static Unmarshaller jaxbUnmarshaller;

	static {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(HistoryType.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new RuntimeException("Cannot initialize JAXB Context", e);
		}
	}

	public static HistoryType getXmlAsObject(String xmlText) {
		StringReader reader = new StringReader(xmlText);

		HistoryType historyType;
		try {
			historyType = (HistoryType) jaxbUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			throw new RuntimeException("Cannot unmarshal HistoryType", e);
		}

		return historyType;
	}

}
