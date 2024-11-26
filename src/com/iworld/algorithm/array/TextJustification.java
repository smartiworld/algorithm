package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 68. Text Justification
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 *
 * Example 1:
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Example 2:
 *
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 * Example 3:
 *
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] consists of only English letters and symbols.
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 *
 * @date 2024/11/20 19:07
 */
public class TextJustification {
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int len = words.length;
        LinkedList<LineWord> lineWords = new LinkedList<>();
        if (len > 1) {
            int preLen = words[0].length();
            int preIndex = 0;
            for (int i = 1; i < len; i++) {
                int curLen = words[i].length();
                int needSpace = (i - preIndex);
                if (preLen + curLen + needSpace > maxWidth) {
                    LineWord lineWord = new LineWord(preIndex, i - 1, maxWidth - preLen);
                    lineWords.addFirst(lineWord);
                    preLen = curLen;
                    preIndex = i;
                } else {
                    preLen += curLen;
                }
                if (i == len - 1) {
                    LineWord lineWord = new LineWord(preIndex, i, maxWidth - preLen);
                    lineWords.addFirst(lineWord);
                }
            }
        } else {
            LineWord lineWord = new LineWord(0, 0, maxWidth - words.length);
            lineWords.addFirst(lineWord);
        }
        int lineSize = lineWords.size();
        int height = 0;
        while (!lineWords.isEmpty()) {
            char[] cLineWord = new char[maxWidth];
            LineWord lineWord = lineWords.pollLast();
            int wordCount = lineWord.endIndex - lineWord.startIndex + 1;
            int spaceCount = lineWord.needSpace;
            int spaceBlock = wordCount - 1;
            int remainBlockSpace = spaceCount;
            int everyBlockSpaceCount = spaceBlock == 0 ? spaceCount : spaceCount / spaceBlock;
            if (height < lineSize - 1 && wordCount > 1) {
                int index = maxWidth - 1;
                for (int i = lineWord.endIndex; i >= lineWord.startIndex; i--) {
                    char[] cWords = words[i].toCharArray();
                    for (int j = cWords.length - 1; j >= 0; j--) {
                        cLineWord[index--] = cWords[j];
                    }
                    if (i > lineWord.startIndex) {
                        for (int k = 0; k < everyBlockSpaceCount; k++) {
                            cLineWord[index--] = ' ';
                        }
                        remainBlockSpace -= everyBlockSpaceCount;
                    }
                    int remainSpaceBlock = spaceBlock - lineWord.endIndex + i - 1;
                    if (remainSpaceBlock > 0) {
                        everyBlockSpaceCount = remainBlockSpace / (spaceBlock - lineWord.endIndex + i - 1);
                    }
                }
            } else {
                int index = 0;
                for (int i = lineWord.startIndex; i <= lineWord.endIndex; i++) {
                    char[] cWords = words[i].toCharArray();
                    for (int j = 0; j < cWords.length; j++) {
                        cLineWord[index++] = cWords[j];
                    }
                    if (i < lineWord.endIndex) {
                        cLineWord[index++] = ' ';
                    }
                }
                while (index < cLineWord.length) {
                    cLineWord[index++] = ' ';
                }
            }
            height++;
            String s = new String(cLineWord);
            System.out.println(s);
            ans.add(s);
        }
        return ans;
    }
    
    public static class LineWord {
        public int startIndex;
        
        public int endIndex;
        
        public int needSpace;
        
        public int len;
        
        public LineWord() {
        
        }
    
        public LineWord(int startIndex, int endIndex, int needSpace) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.needSpace = needSpace;
        }
        
        public LineWord(int startIndex, int endIndex, int needSpace, int len) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.needSpace = needSpace;
            this.len = len;
        }
    
        @Override
        public String toString() {
            return "LineWord{" +
                    "startIndex=" + startIndex +
                    ", endIndex=" + endIndex +
                    ", needSpace=" + needSpace +
                    ", len=" + len +
                    '}';
        }
    }
    
    public static void main(String[] args) {
        //ask   not   what
        //your country can
        //do  for  you ask
        //what  you can do
        String[] words = {"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
        int maxWidth = 16;
        TextJustification textJustification = new TextJustification();
        System.out.println(textJustification.fullJustify(words, maxWidth));
    }
}
