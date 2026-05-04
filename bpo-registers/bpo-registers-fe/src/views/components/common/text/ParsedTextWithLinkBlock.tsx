import React from "react";
import styled from "styled-components";
import { Box, Grid, Typography, Link } from "@mui/material";

const Label = styled(Box)`
  font-size: 0.95rem;
  color: ${(props) => props.theme.palette.body.color};
`;

const Text = styled(Box)`
  font-size: 0.875rem;
  word-break: break-word;
`;

interface ParsedTextWithLinkBlockProps {
  label: string;
  text: string;
  hrefBase: string;
  delimiter?: [string, string];
  gridProps?: { xs?: number; sm?: number; md?: number; lg?: number };
}

const ParsedTextWithLinkBlock: React.FC<ParsedTextWithLinkBlockProps> = ({
  label,
  text,
  hrefBase,
  delimiter = ["[[", "]]"],
  gridProps = { xs: 12, sm: 12, md: 6, lg: 4 },
}) => {
  const [startDelim, endDelim] = delimiter;

  const pattern = new RegExp(`${escapeRegex(startDelim)}(.*?)${escapeRegex(endDelim)}`);
  const match = text.match(pattern);

  if (!match) {
    return (
      <Grid item {...gridProps}>
        <Typography variant="body2" component="div" gutterBottom>
          <Label fontWeight="fontWeightBold">{label}</Label>
          <Text fontWeight="fontWeightRegular">{text}</Text>
        </Typography>
      </Grid>
    );
  }

  const linkToken = match[0];
  const id = match[1];
  const [before, after] = text.split(linkToken);

  return (
    <Grid item {...gridProps}>
      <Typography variant="body2" component="div" gutterBottom>
        <Label fontWeight="fontWeightBold">{label}</Label>
        <Text fontWeight="fontWeightRegular">
          {before}
          <Link
            href={`${hrefBase}${id}`}
            target="_blank"
            style={{ wordBreak: "break-word", textDecoration: "none", color: "#1976d2" }}
          >
            {id}
          </Link>
          {after}
        </Text>
      </Typography>
    </Grid>
  );
};

const escapeRegex = (str: string) => str.replace(/[-/\\^$*+?.()|[\]{}]/g, "\\$&");

export default ParsedTextWithLinkBlock;
