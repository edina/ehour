/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package net.rrm.ehour.sort;

import net.rrm.ehour.domain.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {
    private boolean firstNameFirst;

    public UserComparator(boolean firstNameFirst) {
        this.firstNameFirst = firstNameFirst;
    }

    public int compare(User o1, User o2) {
        int cmp;

        if (firstNameFirst) {
            cmp = o1.getFirstName().compareToIgnoreCase(o2.getFirstName());

            if (cmp == 0) {
                cmp = o1.getLastName().compareToIgnoreCase(o2.getLastName());
            }
        } else {
            cmp = o1.getLastName().compareToIgnoreCase(o2.getLastName());

            if (cmp == 0) {
                cmp = o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
            }
        }

        return cmp;
    }
}
