package assignment.business;

public enum UserAccessPriveledge {
	GUEST, MEMBER, MODERATOR;
	
	
	
	public String toString() {
		switch (this) {
		case GUEST: return "Guest";
		case MEMBER: return "Member";
		case MODERATOR: return "Moderator";
		default: return "";
		}
	}
}
