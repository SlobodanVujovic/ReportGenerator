package reportGenerator;

public class KeyForAffectedSite {
	String firstPartOfHeader;
	String secondPartOfHeader;
	int columnNumber;

	public KeyForAffectedSite(String firstPartOfHeader, String secondPartOfHeader, int columnNumber) {
		this.firstPartOfHeader = firstPartOfHeader;
		this.secondPartOfHeader = secondPartOfHeader;
		this.columnNumber = columnNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columnNumber;
		result = prime * result + ((firstPartOfHeader == null) ? 0 : firstPartOfHeader.hashCode());
		result = prime * result + ((secondPartOfHeader == null) ? 0 : secondPartOfHeader.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof KeyForAffectedSite))
			return false;
		KeyForAffectedSite other = (KeyForAffectedSite) obj;
		if (columnNumber != other.columnNumber)
			return false;
		if (firstPartOfHeader == null) {
			if (other.firstPartOfHeader != null)
				return false;
		} else if (!firstPartOfHeader.equals(other.firstPartOfHeader))
			return false;
		if (secondPartOfHeader == null) {
			if (other.secondPartOfHeader != null)
				return false;
		} else if (!secondPartOfHeader.equals(other.secondPartOfHeader))
			return false;
		return true;
	}
}
