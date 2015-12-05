package assignment.business;

public enum UserAccessPriveledge {
	GUEST, MEMBER, MODERATOR, CONTENT_MANAGER;
	
	
	
	public String toString() {
		switch (this) {
		case GUEST: return "Guest";
		case MEMBER: return "Member";
		case MODERATOR: return "Moderator";
		case CONTENT_MANAGER: return "ContentManager";
		default: return "";
		}
	}
}
