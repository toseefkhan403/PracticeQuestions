package com.practice.strings;

public class CompareVersionNumbers {
    // first get the revisions - be on the same rev on both the versions - then
    // compare - O(n+m), O(1)
    public int compareVersion(String version1, String version2) {
        // using int instead of stringbuilder
        // int can overflow if rev is > int max
        int revision1 = 0;
        int revision2 = 0;

        int i = 0;
        int j = 0;

        while (i < version1.length() || j < version2.length()) {
            while (i < version1.length() && version1.charAt(i) != '.') {
                revision1 = revision1 * 10 + version1.charAt(i) - '0';
                i++;
            }

            while (j < version2.length() && version2.charAt(j) != '.') {
                revision2 = revision2 * 10 + version2.charAt(j) - '0';
                j++;
            }

            if (revision1 < revision2) {
                return -1;
            } else if (revision1 > revision2) {
                return 1;
            }

            // reset values
            i++;
            j++;
            // reset to 0 - if version ends, its 0 ahead by default
            revision1 = 0;
            revision2 = 0;
        }

        // both versions are equal
        return 0;
    }

    // first get the revisions - be on the same rev on both the versions - then
    // compare - O(n+m), O(1)
    public int compareVersionI(String version1, String version2) {
        StringBuilder revision1 = new StringBuilder();
        StringBuilder revision2 = new StringBuilder();

        int i = 0;
        int j = 0;

        while (i < version1.length() || j < version2.length()) {
            while (i < version1.length() && version1.charAt(i) != '.') {
                revision1.append(version1.charAt(i));
                i++;
            }

            while (j < version2.length() && version2.charAt(j) != '.') {
                revision2.append(version2.charAt(j));
                j++;
            }

            // use int - no need to convert here
            if (Integer.parseInt(revision1.toString()) < Integer.parseInt(revision2.toString())) {
                return -1;
            } else if (Integer.parseInt(revision1.toString()) > Integer.parseInt(revision2.toString())) {
                return 1;
            }

            // reset values
            i++;
            j++;
            revision1.setLength(0);
            // reset to 0 - if version ends, its 0 ahead by default
            revision1.append('0');
            revision2.setLength(0);
            revision2.append('0');
        }

        // both versions are equal
        return 0;
    }

}
