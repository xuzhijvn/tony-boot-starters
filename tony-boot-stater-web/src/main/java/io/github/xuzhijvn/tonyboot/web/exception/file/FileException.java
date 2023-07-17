package io.github.xuzhijvn.tonyboot.web.exception.file;

import io.github.xuzhijvn.tonyboot.tools.exception.base.BaseException;

/**
 * 文件信息异常类
 *
 * @author tony
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
